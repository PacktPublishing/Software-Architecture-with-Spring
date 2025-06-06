package com.packtpub.authenticationservices.internal.usecases;

import com.packtpub.authenticationservices.internal.repositories.AuthenticationManagerRepository;
import com.packtpub.authenticationservices.internal.repositories.TokenRepository;
import com.packtpub.authenticationservices.internal.repositories.UserRepository;
import reactor.core.publisher.Mono;

public class GenerateTokenUseCase {

    private final AuthenticationManagerRepository authenticationManagerRepository;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    public GenerateTokenUseCase(AuthenticationManagerRepository authenticationManagerRepository, UserRepository userRepository, TokenRepository tokenRepository) {
        this.authenticationManagerRepository = authenticationManagerRepository;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public Mono<String> execute(String username, String password) {
        return authenticationManagerRepository.authenticate(username, password)
                .flatMap(authentication -> userRepository.getRolesByUsername(username)
                        .collectList() // Collect roles into a list
                        .doOnNext(authentication::setRoles) // Set roles to the Authentication object
                        .thenReturn(authentication)) // Return the updated Authentication object
                .flatMap(authentication -> Mono.just(tokenRepository.generate(authentication))); // Wrap result in Mono
    }

}
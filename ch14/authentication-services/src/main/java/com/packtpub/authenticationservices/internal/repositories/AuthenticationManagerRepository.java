package com.packtpub.authenticationservices.internal.repositories;

import com.packtpub.authenticationservices.internal.entities.Authentication;
import reactor.core.publisher.Mono;

public interface AuthenticationManagerRepository {
    Mono<Authentication> authenticate(String username, String password);
}

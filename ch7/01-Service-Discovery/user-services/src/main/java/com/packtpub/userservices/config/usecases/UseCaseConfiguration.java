package com.packtpub.userservices.config.usecases;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import com.packtpub.userservices.adapter.datasources.authentication.AuthenticationRestApi;
import com.packtpub.userservices.adapter.datasources.user.UserJpaDatasource;
import com.packtpub.userservices.adapter.datasources.user.UserJpaRepository;
import com.packtpub.userservices.internal.repositories.UserRepository;
import com.packtpub.userservices.internal.usecases.GetUserRolesUseCase;
import com.packtpub.userservices.internal.usecases.GetUsersUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public GetUserRolesUseCase getUserRolesUseCase(UserJpaRepository userJpaRepository){
        UserRepository userRepository = new UserJpaDatasource(userJpaRepository);
        return new GetUserRolesUseCase(userRepository);
    }

    @Bean
    public GetUsersUseCase getUsersUseCase(UserJpaRepository userJpaRepository){
        UserRepository userRepository = new UserJpaDatasource(userJpaRepository);
        return new GetUsersUseCase(userRepository);
    }

    @Bean
    public AuthenticationRestApi authenticationRestApi(RestClient restClient, DiscoveryClient discoveryClient){
        return new AuthenticationRestApi(restClient, discoveryClient);
    }

}
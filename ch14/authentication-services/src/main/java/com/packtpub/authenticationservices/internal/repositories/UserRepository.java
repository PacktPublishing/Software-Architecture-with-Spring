package com.packtpub.authenticationservices.internal.repositories;

import reactor.core.publisher.Flux;

public interface UserRepository {
    Flux<String> getRolesByUsername(String username);
}

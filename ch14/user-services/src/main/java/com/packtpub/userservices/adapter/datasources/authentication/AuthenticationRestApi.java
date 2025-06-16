package com.packtpub.userservices.adapter.datasources.authentication;

import com.packtpub.userservices.internal.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationRestApi {

    private final RestClient.Builder restClient;

    // Use Kubernetes DNS for service discovery
    @Value("${services.authentication.url}")
    private String authenticationServiceUrl;


    public AuthenticationUser validateToken(String token) {

        AuthenticationUser authenticationUser = restClient.build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host(authenticationServiceUrl)
                        .path("/v1/api/auth/validate")
                        .queryParam("token", token)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new BusinessException(response.getStatusCode().toString(), response.getStatusText());
                })
                .body(AuthenticationUser.class);
        return authenticationUser;
    }
}
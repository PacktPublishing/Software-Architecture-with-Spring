package com.packtpub.userservices.adapter.datasources.authentication;

import com.packtpub.userservices.internal.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

public class AuthenticationRestApi {

    private final RestClient restClient;

    @Value("${authentication.service.url}")
    private String authenticationServiceUrl;

    public AuthenticationRestApi(RestClient restClient) {
        this.restClient = restClient;
    }

    public AuthenticationUser validateToken(String token) {
        return restClient.get()
                .uri(authenticationServiceUrl + "/v1/api/auth/validate?token={token}", token)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new BusinessException(response.getStatusCode().toString(), response.getStatusText());
                })
                .body(AuthenticationUser.class);
    }

}

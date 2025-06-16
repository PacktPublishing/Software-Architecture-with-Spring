package com.packtpub.productservices.adapter.datasources.authentication;

import com.packtpub.productservices.internal.exception.BusinessException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AuthenticationRestApi {


    private final RestClient.Builder restClient;

    public AuthenticationRestApi(RestClient.Builder restClient) {
        this.restClient = restClient;
    }

    public AuthenticationUser validateToken(String token) {

        return restClient.build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("AUTHENTICATION-SERVICES")
                        .path("/v1/api/auth/validate")
                        .queryParam("token", token)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new BusinessException(response.getStatusCode().toString(), response.getStatusText());
                })
                .body(AuthenticationUser.class);
    }
}

package com.packtpub.userservices.adapter.datasources.authentication;

import com.packtpub.userservices.config.correlation.CorrelationIdUtil;
import com.packtpub.userservices.internal.exceptions.BusinessException;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.propagation.TextMapSetter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationRestApi {

    private final RestClient.Builder restClient;

    public AuthenticationUser validateToken(String token) {

        AuthenticationUser authenticationUser = restClient.build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("AUTHENTICATION-SERVICES")
                        .path("/v1/api/auth/validate")
                        .queryParam("token", token)
                        .build())
                .headers(httpHeaders -> {
                    // Propagate trace context
                    Context currentContext = Context.current();
                    GlobalOpenTelemetry.getPropagators().getTextMapPropagator()
                            .inject(currentContext, httpHeaders, HttpHeadersSetter.INSTANCE);
                    // Custom correlation ID (optional)
                    httpHeaders.add("x-correlation-id", CorrelationIdUtil.getCorrelationId());
                })
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new BusinessException(response.getStatusCode().toString(), response.getStatusText());
                })
                .body(AuthenticationUser.class);
        log.info("Outgoing traceparent: {}", Span.current().getSpanContext().getTraceId());
        return authenticationUser;
    }

    // TextMapSetter for injecting headers
    private static final class HttpHeadersSetter implements TextMapSetter<HttpHeaders> {
        static final HttpHeadersSetter INSTANCE = new HttpHeadersSetter();

        @Override
        public void set(org.springframework.http.HttpHeaders carrier, String key, String value) {
            if (carrier != null) {
                carrier.add(key, value);
            }
        }
    }

}

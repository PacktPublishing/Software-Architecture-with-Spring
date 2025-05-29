package com.packtpub.authenticationservices.adapter.datasources;

import com.packtpub.authenticationservices.adapter.transportlayers.restapi.dto.response.RoleResponse;
import com.packtpub.authenticationservices.internal.repositories.UserRepository;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserRestApi implements UserRepository {

    private final RestClient restClient;

    public UserRestApi(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    // Only circuit breaker
    //@CircuitBreaker(name = "userServices")

    // Circuit breaker with fallback
    @CircuitBreaker(name = "userServices", fallbackMethod = "getRolesFromCache")

    // Retry
    @Retry(name = "userServicesRetry", fallbackMethod = "getRolesFromCache")

    // Rate limiter
    @RateLimiter(name = "userServicesRateLimiter")

    // Bulkhead
    @Bulkhead(name = "userServicesBulkhead", type = Bulkhead.Type.SEMAPHORE)
    public List<String> getRolesByUsername(String username) {
        RoleResponse result = restClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("USER-SERVICES")
                        .path("/v1/users/{username}/roles")
                        .build(Map.of("username", username)))
                .retrieve()
                .body(RoleResponse.class);
        return result.getRoles();

    }

    public List<String> getRolesFromCache(String username, Throwable throwable) {
        log.info("Fallback response due to: {}", throwable.getMessage());
        return List.of("ROLE_GUEST");
    }

    public List<String> getRolesFromCacheRetry(Exception e) {
        log.info("Fallback response due to: {}", e.getMessage());
        return List.of("ROLE_GUEST");
    }

}

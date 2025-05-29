package com.packtpub.authenticationservices.adapter.datasources;

import com.packtpub.authenticationservices.internal.repositories.UserRepository;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserRestApi implements UserRepository {

    private final WebClient webClient;

    public UserRestApi(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

	// Only circuit breaker
    //@CircuitBreaker(name = "userServices")

    // Circuit breaker with fallback
    @CircuitBreaker(name = "userServices", fallbackMethod = "getRolesFromCache")

    // Retry
    @Retry(name = "userServicesRetry", fallbackMethod = "getRolesFromCache")

    // Rate limiter
    @RateLimiter(name = "userServicesRateLimiter")

    // Uncomment for testing Bulkhead
    @Bulkhead(name = "userServicesBulkhead", type = Bulkhead.Type.SEMAPHORE)
    public Flux<String> getRolesByUsername(String username) {
        return webClient.get()
                .uri("http://USER-SERVICES/v1/users/{username}/roles", username)
                .retrieve()
                .onStatus(status -> status != HttpStatus.OK,
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new RuntimeException("Error: " + errorBody))))
                .bodyToFlux(String.class);
    }

    public Flux<String> getRolesFromCache(String username, Throwable throwable) {
        log.info("Fallback response due to: {}", throwable.getMessage());
        return Flux.just("ROLE_GUEST");
    }

    public Flux<String> getRolesFromCacheRetry(String username, Throwable throwable) {
        log.info("Fallback response due to: {}", throwable.getMessage());
        return Flux.just("ROLE_GUEST");
    }

}

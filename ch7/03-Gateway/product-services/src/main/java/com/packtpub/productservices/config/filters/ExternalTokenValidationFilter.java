package com.packtpub.productservices.config.filters;

import com.packtpub.productservices.adapter.datasources.authentication.AuthenticationRestApi;
import com.packtpub.productservices.adapter.datasources.authentication.AuthenticationUser;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ExternalTokenValidationFilter extends OncePerRequestFilter {

    private final HandlerExceptionResolver handlerExceptionResolver;
    private final AuthenticationRestApi authenticationRestApi;

    private static final List<Pattern> PUBLIC_PATH_PATTERNS = List.of(
            Pattern.compile("^/swagger-ui/index.html$"),
            Pattern.compile("^/swagger-ui/.*$"),
            Pattern.compile("^/v3/api-docs$"),
            Pattern.compile("^/v3/api-docs/swagger-config$")
    );

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) {

        try {
            final String authHeader = request.getHeader("Authorization");
            String requestURI = request.getRequestURI();

            if (PUBLIC_PATH_PATTERNS.stream().anyMatch(pattern -> pattern.matcher(requestURI).matches())) {
                filterChain.doFilter(request, response);
                return;
            }

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new BadCredentialsException("Token not found");
            }

            AuthenticationUser authenticationUser = authenticationRestApi.validateToken(authHeader);

            if (authenticationUser != null) {
                List<SimpleGrantedAuthority> authorities = authenticationUser.getRoles().stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(authenticationUser.getRoles(), null, authorities));
            } else {
                throw new ExpiredJwtException(null, null, authHeader);
            }

            filterChain.doFilter(request, response);

        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}
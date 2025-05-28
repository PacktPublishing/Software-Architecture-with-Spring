package com.packtpub.authenticationservices.adapter.datasources;

import com.packtpub.authenticationservices.internal.entities.Authentication;
import com.packtpub.authenticationservices.internal.repositories.TokenRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Service
@Data
public class TokenJwt implements TokenRepository {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;


    @Override
    public String generate(Authentication user) {

        return Jwts.builder()
                .header().type("JWT").and()
                .claim("roles", user.getRoles())
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    @Override
    public Authentication validate(String token) {
        try {
            token = token.replaceFirst("Bearer ", "");
            final Claims claims = extractAllClaims(token);
            return new Authentication(claims.getSubject(), (List) claims.get("roles"));
        } catch (Exception ex) {
            throw new BadCredentialsException("Invalid JWT token", ex);
        }
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        Jwt<?, ?> jwt = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parse(token);
        return (Claims) jwt.getPayload();
    }

}
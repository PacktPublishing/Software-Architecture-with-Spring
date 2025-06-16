package com.packtpub.authorizationserver.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;



@Configuration
public class SecurityFilterConfig {

  @Order(1)
  @Bean
  SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {

    // DEPRECATED AND REPLACED WITH THE CODE BELOW
    // OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
    // http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(Customizer.withDefaults());

    ///  Code below replaces the deprecated OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
    var authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();

    http
            // Apply this security filter chain only to requests matching the Authorization Server endpoints
            .securityMatcher(authorizationServerConfigurer.getEndpointsMatcher())
            // Require authentication for any request to those endpoints
            .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
            // Register the Authorization Server configurer and enable OpenID Connect (OIDC) support
            .with(authorizationServerConfigurer, configurer -> {
              configurer.oidc(Customizer.withDefaults());
            });
    ///  Code above replaces the deprecated OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

    http.exceptionHandling((exceptions) -> exceptions
                    .authenticationEntryPoint(
                            new LoginUrlAuthenticationEntryPoint("/login")))
            .oauth2ResourceServer(conf -> conf.jwt(Customizer.withDefaults()));

    return http.build();
  }

  @Order(2)
  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
          throws Exception {
    http
            .authorizeHttpRequests((authorize) -> authorize
                    .anyRequest().authenticated())
            .formLogin(Customizer.withDefaults());

    return http.build();
  }

}

package com.packtpub.productservices.config.usecases;

import com.packtpub.productservices.adapter.datasources.authentication.AuthenticationRestApi;
import com.packtpub.productservices.adapter.datasources.product.ProductJpaDatasource;
import com.packtpub.productservices.adapter.datasources.product.ProductJpaRepository;
import com.packtpub.productservices.internal.repositories.ProductRepository;
import com.packtpub.productservices.internal.usecases.GetProductsUseCase;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public GetProductsUseCase getUsersUseCase(ProductJpaRepository userDatabaseRepository){
        ProductRepository userGateway = new ProductJpaDatasource(userDatabaseRepository);
        return new GetProductsUseCase(userGateway);
    }

    @Bean
    public AuthenticationRestApi authenticationRestApi(RestClient.Builder restClient){
        return new AuthenticationRestApi(restClient);
    }

}
package com.packtpub.productservices.adapter.datasources.product.service;

import com.packtpub.productservices.adapter.transportlayers.restapi.dto.request.ProductRequest;
import com.packtpub.productservices.adapter.transportlayers.restapi.dto.response.ProductResponse;
import com.packtpub.productservices.internal.entity.Product;
import com.packtpub.productservices.internal.usecases.AddProductUseCase;
import com.packtpub.productservices.internal.usecases.GetProductsByIdUseCase;
import com.packtpub.productservices.internal.usecases.GetProductsUseCase;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final AddProductUseCase addProductUseCase;
    private final GetProductsByIdUseCase getProductsByIdUseCase;
    private final GetProductsUseCase getProductsUseCase;

    public ProductServiceImpl(AddProductUseCase addProductUseCase, GetProductsByIdUseCase getProductsByIdUseCase, GetProductsUseCase getProductsUseCase) {
        this.addProductUseCase = addProductUseCase;
        this.getProductsByIdUseCase = getProductsByIdUseCase;
        this.getProductsUseCase = getProductsUseCase;
    }

    @Cacheable("products")
    public List<ProductResponse>    getAllProducts() {
        List<Product> products = getProductsUseCase.execute();

        List<ProductResponse> productResponses = new ArrayList<>();

        for (Product product : products) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setDescription(product.getDescription());
            productResponse.setUserId(product.getUserId());
            productResponse.setPhotoBase64(product.getPhotoBase64());
            productResponses.add(productResponse);
        }

        return productResponses;

    }

    @Cacheable("productsById")
    public ProductResponse getProductById(Long id) {
        Product product = getProductsByIdUseCase.execute(id);
        return new ProductResponse(product.getId(),
                product.getName(), product.getDescription(),
                product.getUserId(), product.getPhotoBase64());
    }

    @CacheEvict(value = "products", allEntries = true)
    @Override
    public ProductResponse add(ProductRequest productRequest) {
        Product product = new Product(null, productRequest.getName(), productRequest.getDescription(),
                productRequest.getPhotoBase64().getBytes(), productRequest.getUserId(), productRequest.getPhotoBase64());
        product = addProductUseCase.execute(product);
        return new ProductResponse(product.getId(),
                product.getName(), product.getDescription(),
                product.getUserId(), product.getPhotoBase64());
    }

}

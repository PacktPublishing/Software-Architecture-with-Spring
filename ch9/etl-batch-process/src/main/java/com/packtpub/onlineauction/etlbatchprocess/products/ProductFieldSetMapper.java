package com.packtpub.onlineauction.etlbatchprocess.products;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class ProductFieldSetMapper implements FieldSetMapper<ProductDto> {

    @Override
    public ProductDto mapFieldSet(final FieldSet fieldSet) throws BindException {

        return ProductDto.builder()
                .id(fieldSet.readInt("id"))
                .name(fieldSet.readString("name"))
                .description(fieldSet.readString("description"))
                .userId(fieldSet.readInt("user_id"))
                .build();
    }
}

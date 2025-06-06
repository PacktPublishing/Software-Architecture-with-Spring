package com.packtpub.onlineauction.etlbatchprocess.auctions;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class AuctionFieldSetMapper implements FieldSetMapper<AuctionDto> {

    @Override
    public AuctionDto mapFieldSet(final FieldSet fieldSet) throws BindException {
       return AuctionDto.builder()
                .originalId(fieldSet.readString("_id"))
                .description(fieldSet.readString("description"))
                .maxbid(fieldSet.readBigDecimal("maxbid"))
                .minbid(fieldSet.readBigDecimal("minbid"))
                .productid(fieldSet.readLong("productid"))
                .isActive(fieldSet.readBoolean("active"))
                .createdAt(parseDate(fieldSet.readString("createdat")))
                .build();
    }
    private LocalDateTime parseDate(String dateString) {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        return offsetDateTime.toLocalDateTime();
    }
}

package com.java.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AdvertisementDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private String categoryTitle;
    private String userName;

    public AdvertisementDto(Long id, String title, BigDecimal price, String categoryTitle, String userName) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.categoryTitle = categoryTitle;
        this.userName = userName;
    }
}
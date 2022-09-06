package com.guipalm4.domain.product;

import com.guipalm4.infrastructure.persistence.ProductJpaEntity;

public class ProductInputDTO {

    private final String name;
    private final Double price;
    private final Integer quantityInStock;

    public ProductInputDTO(final String name, final Double price, final Integer quantityInStock) {
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }
}

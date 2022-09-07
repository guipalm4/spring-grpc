package com.guipalm4.domain.product;

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

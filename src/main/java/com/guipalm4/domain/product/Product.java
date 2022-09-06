package com.guipalm4.domain.product;


import java.util.UUID;

public class Product {

    private String id;
    private String name;
    private Double price;
    private Integer quantityInStock;

    private Product(
            final String id,
            final String name,
            final Double price,
            final Integer quantityInStock
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public static Product newProduct(
            final String aName,
            final Double aPrice,
            final Integer quantityInStock
    ) {
        var id = UUID.randomUUID().toString();

        return new Product(id, aName, aPrice, quantityInStock);
    }

    public static Product with(
            final String anId,
            final String aName,
            final Double aPrice,
            final Integer quantityInStock
    ) {
        return new Product(anId, aName, aPrice, quantityInStock);
    }

    public String getId() {
        return id;
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

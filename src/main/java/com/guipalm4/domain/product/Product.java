package com.guipalm4.domain.product;


import java.util.UUID;

public class Product {

    private final String id;
    private final String name;
    private final Double price;
    private final Integer stock;

    private Product(
            final String id,
            final String name,
            final Double price,
            final Integer stock
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public static Product newProduct(
            final String aName,
            final Double aPrice,
            final Integer stock
    ) {
        var id = UUID.randomUUID().toString();

        return new Product(id, aName, aPrice, stock);
    }

    public static Product with(
            final String anId,
            final String aName,
            final Double aPrice,
            final Integer stock
    ) {
        return new Product(anId, aName, aPrice, stock);
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

    public Integer getStock() {
        return stock;
    }
}

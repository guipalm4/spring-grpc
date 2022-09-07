package com.guipalm4.domain.product;

import com.guipalm4.infrastructure.persistence.ProductJpaEntity;

public class ProductOutputDTO {

    private final String id;

    private final String name;
    private final Double price;
    private final  Integer quantityInStock;

    public ProductOutputDTO(final String id, final String name, final Double price, final Integer quantityInStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public static ProductOutputDTO fromEntity(ProductJpaEntity productJpaEntity) {
        return new ProductOutputDTO(
                productJpaEntity.getId(),
                productJpaEntity.getName(),
                productJpaEntity.getPrice(),
                productJpaEntity.getStock()
        );
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

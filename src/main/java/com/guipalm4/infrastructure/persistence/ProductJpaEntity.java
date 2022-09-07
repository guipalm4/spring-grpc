package com.guipalm4.infrastructure.persistence;

import com.google.common.base.Objects;
import com.guipalm4.domain.product.Product;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductJpaEntity {

    @Id
    private String id;
    private String name;
    private Double price;
    private Integer stock;

    public ProductJpaEntity() {
    }

    private ProductJpaEntity(
            final String id,
            final String name,
            final Double price,
            final Integer stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product toDomain() {
        return Product.with(
                getId(),
                getName(),
                getPrice(),
                getStock()
        );
    }

    public static ProductJpaEntity from(Product aProduct) {
        return new ProductJpaEntity(
                aProduct.getId(),
                aProduct.getName(),
                aProduct.getPrice(),
                aProduct.getStock()
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

    public Integer getStock() {
        return stock;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ProductJpaEntity that = (ProductJpaEntity) o;
        return Objects.equal(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}

package com.guipalm4.application.service.impl;

import com.guipalm4.application.service.ProductService;
import com.guipalm4.domain.exception.AlreadyExistException;
import com.guipalm4.domain.exception.NotFoundException;
import com.guipalm4.domain.product.Product;
import com.guipalm4.domain.product.ProductInputDTO;
import com.guipalm4.domain.product.ProductOutputDTO;
import com.guipalm4.infrastructure.persistence.ProductJpaEntity;
import com.guipalm4.infrastructure.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductOutputDTO create(final ProductInputDTO aProduct) {

        checkDuplicity(aProduct.getName());

        var product = Product.newProduct(
                aProduct.getName(), aProduct.getPrice(), aProduct.getQuantityInStock()
        );

        var productCreated = this.productRepository.save(ProductJpaEntity.from(product));

        return ProductOutputDTO.fromEntity(productCreated);
    }

    public ProductOutputDTO findById(final String anId) {

        return productRepository.findById(anId).map(ProductOutputDTO::fromEntity)
                .orElseThrow(()-> new NotFoundException(anId));
    }

    public void delete(final String anId) {

    }

    public List<ProductOutputDTO> findAll() {
        return null;
    }

    private void checkDuplicity(String name) {
        this.productRepository.findByNameIgnoreCase(name)
                .ifPresent(ex-> {
                    throw new AlreadyExistException(name);
                });
    }
}

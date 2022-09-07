package com.guipalm4.application.service;

import com.guipalm4.application.service.impl.ProductServiceImpl;
import com.guipalm4.domain.exception.AlreadyExistException;
import com.guipalm4.domain.exception.NotFoundException;
import com.guipalm4.domain.product.Product;
import com.guipalm4.domain.product.ProductInputDTO;
import com.guipalm4.infrastructure.persistence.ProductJpaEntity;
import com.guipalm4.infrastructure.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImpTest {

    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;

    @Test
    @DisplayName("should create a product when valid data is provided")
    void createProductSuccessTest() {

        final var request = new ProductInputDTO("Product A", 20.00, 5);
        final var expectedProduct = ProductJpaEntity.from(
                Product.newProduct(
                    "Product A",
                    20.00,
                    5
                )
        );

        Mockito.when(productRepository.save(Mockito.any())).thenReturn(expectedProduct);

        Assertions.assertThat(expectedProduct.getId()).isNotNull();

        final var response = productService.create(request);

        Assertions.assertThat(response)
                .usingRecursiveComparison()
                .comparingOnlyFields("id", "name", "price", "stock")
                .isEqualTo(expectedProduct);
    }

    @Test
    @DisplayName("should throw AlreadyExistException when call create a product with duplicated name")
    void createProductAlreadyExistTest() {

        final var request = new ProductInputDTO("Product A", 20.00, 5);
        final var expectedProduct = ProductJpaEntity.from(
                Product.newProduct(
                        "Product A",
                        20.00,
                        5
                )
        );

        Mockito.when(productRepository.findByNameIgnoreCase(Mockito.any()))
                .thenReturn(Optional.of(expectedProduct));

        Assertions.assertThatExceptionOfType(AlreadyExistException.class)
                .isThrownBy(() -> productService.create(request));
    }

    @Test
    @DisplayName("should return a product when id exist")
    void findByIdTest() {

        final var id  = "8171ccb6-4165-41ee-9774-8b804be0e9f1";
        final var expectedProduct = ProductJpaEntity.from(
                Product.with(
                        id,
                        "Product A",
                        20.00,
                        5
                )
        );

        Mockito.when(productRepository.findById(id))
                .thenReturn(Optional.of(expectedProduct));

        final var response = productService.findById(id);

        Assertions.assertThat(response)
                .usingRecursiveComparison()
                .comparingOnlyFields("id", "name", "price", "stock")
                .isEqualTo(expectedProduct);

    }

    @Test
    @DisplayName("should throw NotFoundException when a id not exist")
    void findByIdThrowingNotFoundExceptionTest() {

        final var id  = "8171ccb6-4165-41ee-9774-8b804be0e9f1";

        Mockito.when(productRepository.findById(Mockito.any()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> productService.findById(id));
    }
}



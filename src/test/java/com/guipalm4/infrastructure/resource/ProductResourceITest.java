package com.guipalm4.infrastructure.resource;

import com.guipalm4.ProductRequest;
import com.guipalm4.ProductServiceGrpc;
import com.guipalm4.domain.exception.AlreadyExistException;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.assertj.core.api.Assertions;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@DirtiesContext
@TestPropertySource("classpath:application-test.properties")
public class ProductResourceITest {

    @GrpcClient("inProcess")
    private ProductServiceGrpc.ProductServiceBlockingStub productServiceGrpc;

    @Autowired
    private Flyway flyway;

    @BeforeEach
    void setUp() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    @DisplayName("should create a product when valid data is provided")
    void createProductTest() {

        final var request= ProductRequest.newBuilder()
                .setName("Wireless Keyboard")
                .setPrice(200)
                .setStock(25)
                .build();

        final var response = productServiceGrpc.create(request);

        Assertions.assertThat(request)
                .usingRecursiveComparison()
                .comparingOnlyFields("name", "price", "stock")
                .isEqualTo(response);
    }

    @Test
    @DisplayName("should throw AlreadyExistException when call create a product with duplicated name")
    void createProductAlreadyExistTest() {

        final var request= ProductRequest.newBuilder()
                .setName("Mouse Wireless")
                .setPrice(200)
                .setStock(25)
                .build();


        Assertions.assertThatExceptionOfType(StatusRuntimeException.class)
                .isThrownBy(() -> productServiceGrpc.create(request))
                .withMessage("ALREADY_EXISTS: Product 'Mouse Wireless' already exist.");
    }
}

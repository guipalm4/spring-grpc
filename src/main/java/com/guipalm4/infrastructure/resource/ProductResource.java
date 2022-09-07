package com.guipalm4.infrastructure.resource;

import com.guipalm4.*;
import com.guipalm4.application.service.ProductService;
import com.guipalm4.domain.product.ProductInputDTO;
import com.guipalm4.domain.product.ProductOutputDTO;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.stream.Collectors;

@GrpcService
public class ProductResource extends ProductServiceGrpc.ProductServiceImplBase {

    private final ProductService productService;

    public ProductResource(final ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void create(final ProductRequest request, final StreamObserver<ProductResponse> responseObserver) {

        final var input = new ProductInputDTO(request.getName(), request.getPrice(), request.getStock());
        final var output = this.productService.create(input);
        final var response= ProductResponse.newBuilder()
                .setId(output.getId())
                .setName(output.getName())
                .setPrice(output.getPrice())
                .setStock(output.getStock())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findById(final RequestById request, final StreamObserver<ProductResponse> responseObserver) {

        final var id = request.getId();
        final var output = this.productService.findById(id);
        final var response = covertToProductResponse(output);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void delete(final RequestById request, final StreamObserver<EmptyResponse> responseObserver) {
        final var id = request.getId();
        this.productService.delete(id);

        responseObserver.onNext(EmptyResponse.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void findAll(final EmptyRequest request, final StreamObserver<ProductResponseList> responseObserver) {

        final var output = productService.findAll();
        final var builder = ProductResponseList.newBuilder();

        output.stream()
                .map(this::covertToProductResponse)
                .forEach(builder::addProducts);

        final var response = builder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    private ProductResponse covertToProductResponse(ProductOutputDTO productOutputDTO) {
        return ProductResponse.newBuilder()
                .setId(productOutputDTO.getId())
                .setName(productOutputDTO.getName())
                .setPrice(productOutputDTO.getPrice())
                .setStock(productOutputDTO.getStock())
                .build();

    }
}

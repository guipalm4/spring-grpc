package com.guipalm4.infrastructure.resource;

import com.guipalm4.ProductRequest;
import com.guipalm4.ProductResponse;
import com.guipalm4.ProductServiceGrpc;
import com.guipalm4.RequestById;
import com.guipalm4.application.service.ProductService;
import com.guipalm4.domain.product.ProductInputDTO;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

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
                .setStock(output.getQuantityInStock())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public void findById(final RequestById request, final StreamObserver<ProductResponse> responseObserver) {

        final var id = request.getId();
        final var output = this.productService.findById(id);
        final var response = ProductResponse.newBuilder()
                .setId(output.getId())
                .setName(output.getName())
                .setPrice(output.getPrice())
                .setStock(output.getQuantityInStock())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public void deleteById(final RequestById request, final StreamObserver<ProductResponse> responseObserver) {

        final var id = request.getId();

    }
}

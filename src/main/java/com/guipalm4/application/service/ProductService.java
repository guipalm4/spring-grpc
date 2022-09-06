package com.guipalm4.application.service;

import com.guipalm4.domain.product.ProductInputDTO;
import com.guipalm4.domain.product.ProductOutputDTO;

import java.util.List;

public interface ProductService {

    ProductOutputDTO create(ProductInputDTO aProduct);
    ProductOutputDTO findById(String anId);
    void delete(String anId);
    List<ProductOutputDTO> findAll();
}

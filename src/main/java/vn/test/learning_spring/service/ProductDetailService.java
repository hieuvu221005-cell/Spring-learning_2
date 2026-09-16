package vn.test.learning_spring.service;

import vn.test.learning_spring.dto.request.ProductDetailCreateReq;
import vn.test.learning_spring.entity.ProductDetail;

import java.util.List;


public interface ProductDetailService {
    List<ProductDetail> getAll();
    ProductDetail createProduct(ProductDetailCreateReq request);
}

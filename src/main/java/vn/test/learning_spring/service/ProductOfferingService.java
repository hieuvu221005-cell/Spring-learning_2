package vn.test.learning_spring.service;

import vn.test.learning_spring.dto.request.ProductOfferingCreateReq;
import vn.test.learning_spring.entity.ProductOfferings;

import java.util.List;

public interface ProductOfferingService {
    ProductOfferings getById(Long id);

    List<ProductOfferings> getAll();

    List<ProductOfferings> findByName(String name);

    List<ProductOfferings> findByNameAndPrice(String name, Long price);

    List<ProductOfferings> findByDetailId(Long id);

    ProductOfferings createProduct(ProductOfferingCreateReq request);

    ProductOfferings updateProduct(Long id, ProductOfferings productOfferings);

    List<ProductOfferings> filter(String name,Long minPrice,Long maxPrice,String color,String status);

}

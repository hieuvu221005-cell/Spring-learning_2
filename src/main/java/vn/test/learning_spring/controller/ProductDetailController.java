package vn.test.learning_spring.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.test.learning_spring.dto.request.ProductDetailCreateReq;
import vn.test.learning_spring.entity.ProductDetail;
import vn.test.learning_spring.service.ProductDetailService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-details")
public class ProductDetailController {

    private final ProductDetailService productDetailService;

    @GetMapping
    public ResponseEntity<List<ProductDetail>> getAll(){
        List<ProductDetail> productDetails = productDetailService.getAll();
        return ResponseEntity.ok(productDetails);
    }

    @PostMapping
    public ResponseEntity<ProductDetail> create(@RequestBody ProductDetailCreateReq request){
         ProductDetail productDetail = productDetailService.createProduct(request);
         return ResponseEntity.ok(productDetail);
    }
}

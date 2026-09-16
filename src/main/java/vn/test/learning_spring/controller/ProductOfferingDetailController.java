package vn.test.learning_spring.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.test.learning_spring.dto.request.AssignProductDetailReq;
import vn.test.learning_spring.entity.ProductOfferings;
import vn.test.learning_spring.service.ProductOfferingDetailService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-details-offerings")
public class ProductOfferingDetailController {


    private final ProductOfferingDetailService productOfferingDetailService;

    @PostMapping
    public ResponseEntity<ProductOfferings> assignProductDetail(@RequestBody AssignProductDetailReq request){
        ProductOfferings productOfferings = productOfferingDetailService.assignProductDetail(request);
        return ResponseEntity.ok(productOfferings);
    }

}

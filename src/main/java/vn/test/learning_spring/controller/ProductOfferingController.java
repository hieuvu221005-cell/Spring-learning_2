package vn.test.learning_spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.test.learning_spring.dto.request.ProductOfferingCreateReq;
import vn.test.learning_spring.entity.ProductOfferings;
import vn.test.learning_spring.service.ProductOfferingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-offerings")
public class ProductOfferingController {


    private final ProductOfferingService productOfferingService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductOfferings> getById(@PathVariable Long id) {
//        id = 1L;
        ProductOfferings productOfferings = productOfferingService.getById(id);
        System.out.println(productOfferings);
        return ResponseEntity.ok(productOfferings);
    }

    @GetMapping
    public ResponseEntity<List<ProductOfferings>> getAllProductOffering(){
        return ResponseEntity.ok(productOfferingService.getAll());
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<List<ProductOfferings>> findByName(String name){
        name = "product_1";
        List<ProductOfferings> productOfferings = productOfferingService.findByName(name);
        return ResponseEntity.ok(productOfferings);
    }

    @GetMapping("/find-by-name-and-price")
    public ResponseEntity<List<ProductOfferings>> findByNameAndPrice(String name, Long price){
        name = "product_1";
        price = 1000L;
        List<ProductOfferings> productOfferings = productOfferingService.findByNameAndPrice(name, price);
        return ResponseEntity.ok(productOfferings);
    }

    @PostMapping
    public ResponseEntity<ProductOfferings> create(@RequestBody ProductOfferingCreateReq request){
        ProductOfferings product = productOfferingService.createProduct(request);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductOfferings> update(@PathVariable Long id,@RequestBody ProductOfferings productOfferings){
        ProductOfferings product = productOfferingService.updateProduct(id,productOfferings);
        return  ResponseEntity.ok(product);
    }

    @GetMapping("/search-by-detail")
    public ResponseEntity<List<ProductOfferings>> findByDetailId(Long id){
        id = 1L;
        List<ProductOfferings> productOfferings = productOfferingService.findByDetailId(id);
        return ResponseEntity.ok(productOfferings);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductOfferings>> filter(@RequestParam(name = "name",required = false) String name,
                                                         @RequestParam(name = "minPrice",required = false) Long minPrice,
                                                         @RequestParam(name = "maxPrice",required = false) Long maxPrice,
                                                         @RequestParam(name = "color",required = false) String color,
                                                         @RequestParam(name = "status",required = false) String status){
       List<ProductOfferings> productOfferingList = productOfferingService.filter(name,minPrice,maxPrice,color,status);
       return ResponseEntity.ok(productOfferingList);
    }
}


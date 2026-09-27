package vn.test.learning_spring.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.test.learning_spring.dto.request.ProductOfferingCreateReq;
import vn.test.learning_spring.dto.request.ProductOfferingFilter;
import vn.test.learning_spring.dto.respone.ProductOfferingRes;
import vn.test.learning_spring.entity.ProductOfferings;
import vn.test.learning_spring.service.ProductOfferingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-offerings")
public class ProductOfferingController {


    private final ProductOfferingService productOfferingService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ProductOfferingRes> getById(@PathVariable Long id) {
//        id = 1L;
        ProductOfferings productOfferings = productOfferingService.getById(id);

        ProductOfferingRes productOfferingRes = modelMapper.map(productOfferings,ProductOfferingRes.class);

//        ProductOfferingRes productOfferingRes = new ProductOfferingRes();
//        productOfferingRes.setId(productOfferings.getId());
//        productOfferingRes.setName(productOfferings.getName());
//        productOfferingRes.setPrice(productOfferings.getPrice());
//        productOfferingRes.setColor(productOfferings.getColor());

        return ResponseEntity.ok(productOfferingRes);
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
    public ResponseEntity<List<ProductOfferingRes>> filter(ProductOfferingFilter productOfferingFilter){
       List<ProductOfferings> productOfferingList = productOfferingService.filter(productOfferingFilter);
       List<ProductOfferingRes> productOfferingResList = modelMapper.map(productOfferingList,new TypeToken<>(){}.getType());
       return ResponseEntity.ok(productOfferingResList);
    }

    @GetMapping
    public ResponseEntity<List<ProductOfferingRes>> getAll(@RequestParam(name = "page_size") Integer pageSize,
                                                         @RequestParam(name = "page_number") Integer pageNumber,
                                                         @RequestParam(name = "sort_by",required = false) String sortBy,
                                                         @RequestParam(name = "sort_style",required = false) String sortStyle){
        List<ProductOfferings> productOfferings = productOfferingService.getAll(pageSize,pageNumber,sortBy,sortStyle).getContent();

//        C2:
        List<ProductOfferingRes> productOfferingResList = modelMapper.map(productOfferings,new TypeToken<List<ProductOfferingRes>>(){
        }.getType());

//        C1:
//        List<ProductOfferingRes> productOfferingResList = new ArrayList<>();
//        for(int i = 0;i < productOfferings.size();i++){
//            ProductOfferings productOffering = productOfferings.get(i);
//            ProductOfferingRes productOfferingRes = modelMapper.map(productOffering,ProductOfferingRes.class);
//
//            productOfferingResList.add(productOfferingRes);
//        }
        return ResponseEntity.ok(productOfferingResList);
    }

}


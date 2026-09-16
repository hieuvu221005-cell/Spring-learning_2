package vn.test.learning_spring.service.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.test.learning_spring.dto.request.AssignProductDetailReq;
import vn.test.learning_spring.entity.ProductDetail;
import vn.test.learning_spring.entity.ProductOfferingDetail;
import vn.test.learning_spring.entity.ProductOfferings;
import vn.test.learning_spring.reponsitory.ProductDetailRepo;
import vn.test.learning_spring.reponsitory.ProductOfferingDetailRepo;
import vn.test.learning_spring.reponsitory.ProductOfferingRepo;
import vn.test.learning_spring.service.ProductOfferingDetailService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductOfferingDetailServiceImp implements ProductOfferingDetailService {


    private final ProductOfferingDetailRepo productOfferingDetailRepo;
    private final ProductOfferingRepo productOfferingRepo;
    private final ProductDetailRepo productDetailRepo;


    @Override
    public ProductOfferings assignProductDetail(AssignProductDetailReq request) {

        List<Long> productDetailIds = new LinkedList<>(new HashSet<>(request.getProductDetailIds()));
        //validate data
        if(request.getProductOfferingId() == null){
            throw new RuntimeException("Thieu du lieu product_offering");
        }
        if(request.getProductDetailIds() == null || request.getProductDetailIds().isEmpty()){
            throw new RuntimeException("Thieu du lieu producy_detail");
        }

        Optional<ProductOfferings> productOfferingOptional = productOfferingRepo.findById(request.getProductOfferingId());
        if(productOfferingOptional.isEmpty()){
            throw new RuntimeException("productOfferingId khong ton tai");
        }
        ProductOfferings productOfferings = productOfferingOptional.get();

        List<ProductDetail> productDetails = productDetailRepo.findAllById(request.getProductDetailIds());
        if(productDetails.isEmpty()){
            throw new RuntimeException("ProductDetailId khong ton tai");
        }

        List<ProductOfferingDetail> productOfferingDetails = new ArrayList<>();
        for(int i = 0;i < productDetails.size();i++){
            ProductOfferingDetail productOfferingDetail = new ProductOfferingDetail();
            productOfferingDetail.setProductOfferings(productOfferingOptional.get());
            productOfferingDetail.setProductDetail(productDetails.get(i));


            productOfferingDetails.add(productOfferingDetail);
        }

        productOfferingDetailRepo.saveAll(productOfferingDetails);
        productOfferings.setProductOfferingDetails(productOfferingDetails);

        return productOfferings;

    }
}

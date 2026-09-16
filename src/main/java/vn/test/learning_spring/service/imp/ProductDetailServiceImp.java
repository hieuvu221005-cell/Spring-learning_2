package vn.test.learning_spring.service.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.test.learning_spring.dto.request.ProductDetailCreateReq;
import vn.test.learning_spring.entity.ProductDetail;
import vn.test.learning_spring.reponsitory.ProductDetailRepo;
import vn.test.learning_spring.service.ProductDetailService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDetailServiceImp implements ProductDetailService {

    private final ProductDetailRepo productDetailRepo;
    @Override
    public List<ProductDetail> getAll() {
        return productDetailRepo.findAll();
    }

    @Override
    public ProductDetail createProduct(ProductDetailCreateReq request) {
        if(request.getFeature() == null || request.getPower() == null || request.getBrand() == null || request.getWeight() == null){
            throw new RuntimeException("chua nhap du du lieu");
        }

        ProductDetail productDetail = new ProductDetail();
        productDetail.setBrand(request.getBrand());
        productDetail.setPower(request.getPower());
        productDetail.setFeature(request.getFeature());
        productDetail.setWeight(request.getWeight());
        productDetail.setImage(request.getImage());
        productDetail.setVideo(request.getVideo());

        return productDetailRepo.save(productDetail);
    }
}

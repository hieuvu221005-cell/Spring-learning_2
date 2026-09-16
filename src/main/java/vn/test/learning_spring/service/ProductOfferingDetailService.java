package vn.test.learning_spring.service;

import vn.test.learning_spring.dto.request.AssignProductDetailReq;
import vn.test.learning_spring.entity.ProductOfferings;

public interface ProductOfferingDetailService {
   ProductOfferings assignProductDetail(AssignProductDetailReq request);
}

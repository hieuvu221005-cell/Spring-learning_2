package vn.test.learning_spring.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.test.learning_spring.entity.ProductDetail;

public interface ProductDetailRepo extends JpaRepository<ProductDetail,Long> {

}

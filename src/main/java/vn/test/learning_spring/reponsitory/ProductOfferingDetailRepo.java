package vn.test.learning_spring.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import vn.test.learning_spring.entity.ProductOfferingDetail;

public interface ProductOfferingDetailRepo extends JpaRepository<ProductOfferingDetail,Long> {

    @Modifying
    @Query("delete  from ProductOfferingDetail p where p.id = :id")
    void deleteById(Long id);

}

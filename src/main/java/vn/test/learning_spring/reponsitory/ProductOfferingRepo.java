package vn.test.learning_spring.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.test.learning_spring.entity.ProductOfferings;

import java.util.List;

public interface ProductOfferingRepo extends JpaRepository<ProductOfferings,Long> {

    List<ProductOfferings> findByName(String name);
    List<ProductOfferings> findByNameAndPrice(String name,Long price);

//    Lay ra danh sach product_offering voi detail_id = 1
//    @Query("select po\n" +
//            "from ProductOfferings po\n" +
//            "where po.id in (select pod.productOfferings\n" +
//            "             from ProductOfferingDetail pod \n" +
//            "             where pod.productDetail.id = 1)")
@Query("select po from ProductOfferings po join ProductOfferingDetail pod on po.id = pod.productOfferings.id where pod.productDetail.id = :id")
      List<ProductOfferings> findByDetailId(Long id);
//    @Query(value = "select *\n" +
//            "from product_offerings po\n" +
//            "where id in (select product_offering_id\n" +
//            "             from product_offering_detail\n" +
//            "             where product_detail_id = 1)",nativeQuery = true)


}
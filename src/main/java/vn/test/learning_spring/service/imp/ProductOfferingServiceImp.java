package vn.test.learning_spring.service.imp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.test.learning_spring.common.StatusEnum;
import vn.test.learning_spring.dto.request.ProductOfferingCreateReq;
import vn.test.learning_spring.entity.ProductOfferings;
import vn.test.learning_spring.reponsitory.ProductOfferingRepo;
import vn.test.learning_spring.service.ProductOfferingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductOfferingServiceImp implements ProductOfferingService {

    private final ProductOfferingRepo productOfferingRepo;
    private final EntityManager entityManager;

    @Override
    public ProductOfferings getById(Long id) {
        Optional<ProductOfferings> product = productOfferingRepo.findById(id);
        if(product.isEmpty()){
           throw  new RuntimeException("Khong tim thay product");
        }else{
            return product.get();
        }
    }
    @Override
    public List<ProductOfferings> getAll(){
            return productOfferingRepo.findAll();
        }

    @Override
    public List<ProductOfferings> findByName(String name) {
        return productOfferingRepo.findByName(name);
    }

    @Override
    public List<ProductOfferings> findByNameAndPrice(String name, Long price) {
        return productOfferingRepo.findByNameAndPrice(name,price);
    }


    @Override
    public ProductOfferings createProduct(ProductOfferingCreateReq request) {
        if(request.getName() == null || request.getColor() == null || request.getPrice() == null){
            throw new RuntimeException("Thieu du lieu");
        }
        ProductOfferings productOfferings = new ProductOfferings();
        productOfferings.setName(request.getName());
        productOfferings.setColor(request.getColor());
        productOfferings.setPrice(request.getPrice());
        productOfferings.setStatus(StatusEnum.ACTIVE);


       return productOfferingRepo.save(productOfferings);
    }

    @Override
    public ProductOfferings updateProduct(Long id, ProductOfferings productOfferings) {
        getById(id);
        productOfferings.setId(id);
        ProductOfferings updateProduct = productOfferingRepo.save(productOfferings);
        return updateProduct;
    }

    @Override
    public List<ProductOfferings> findByDetailId(Long id) {

        return productOfferingRepo.findByDetailId(id);
    }

    @Override
    public List<ProductOfferings> filter(String name, Long minPrice, Long maxPrice, String color, String status) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductOfferings> query = criteriaBuilder.createQuery(ProductOfferings.class);

        Root<ProductOfferings> root = query.from(ProductOfferings.class);

        List<Predicate> predicates = new ArrayList<>();

        if(name != null && !name.isEmpty()){
            Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + name + "%");
            predicates.add(predicate);
        }

        if(minPrice != null){
            Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("price"),minPrice);
            predicates.add(predicate);
        }

        if(maxPrice != null){
            Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("price"),maxPrice);
            predicates.add(predicate);
        }

        if(color != null && !color.isEmpty()){
            Predicate predicate = criteriaBuilder.like(root.get("color"), "%" + color + "%");
            predicates.add(predicate);
        }

        if(status != null && !status.isEmpty()){
            Predicate predicate = criteriaBuilder.equal(root.get("status"), status);
            predicates.add(predicate);
        }


        query.where(predicates.toArray(new Predicate[0]));
        List<ProductOfferings> productOfferings = entityManager.createQuery(query).getResultList();
        return productOfferings;
    }
}

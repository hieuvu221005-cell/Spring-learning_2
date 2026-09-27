package vn.test.learning_spring.service.imp;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.test.learning_spring.common.StatusEnum;
import vn.test.learning_spring.dto.request.ProductOfferingCreateReq;
import vn.test.learning_spring.dto.request.ProductOfferingFilter;
import vn.test.learning_spring.entity.ProductOfferings;
import vn.test.learning_spring.reponsitory.ProductOfferingRepo;
import vn.test.learning_spring.service.ProductOfferingService;
import vn.test.learning_spring.service.spec.ProductOfferingSpecifications;

import java.util.Arrays;
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
    public List<ProductOfferings> filter(ProductOfferingFilter productOfferingFilter) {
        String name = productOfferingFilter.getName();
        Long minPrice = productOfferingFilter.getMinPrice();
        Long maxPrice = productOfferingFilter.getMaxPrice();
        String color = productOfferingFilter.getColor();
        String status = productOfferingFilter.getStatus();

        Specification<ProductOfferings> specification = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        if(name != null && !name.isEmpty()){
            specification = specification.and(ProductOfferingSpecifications.likeName(name));
        }
        if(minPrice != null){
            specification = specification.and(ProductOfferingSpecifications.equalMinPrice(minPrice));
        }
        if(maxPrice != null){
            specification = specification.and(ProductOfferingSpecifications.equalMaxPrice(maxPrice));
        }
        if(color != null && !color.isEmpty()){
            specification = specification.and(ProductOfferingSpecifications.likeColor(color));
        }
        if(status != null && !status.isEmpty()){
            specification = specification.and(ProductOfferingSpecifications.equalStatus(status));
        }

        return productOfferingRepo.findAll(specification);
    }

    //    @Override
//    public List<ProductOfferings> filter(String name, Long minPrice, Long maxPrice, String color, String status) {
//
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<ProductOfferings> query = criteriaBuilder.createQuery(ProductOfferings.class);
//
//        Root<ProductOfferings> root = query.from(ProductOfferings.class);
//
//        List<Predicate> predicates = new ArrayList<>();
//
//        if(name != null && !name.isEmpty()){
//            Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + name + "%");
//            predicates.add(predicate);
//        }
//
//        if(minPrice != null){
//            Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("price"),minPrice);
//            predicates.add(predicate);
//        }
//
//        if(maxPrice != null){
//            Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("price"),maxPrice);
//            predicates.add(predicate);
//        }
//
//        if(color != null && !color.isEmpty()){
//            Predicate predicate = criteriaBuilder.like(root.get("color"), "%" + color + "%");
//            predicates.add(predicate);
//        }
//
//        if(status != null && !status.isEmpty()){
//            Predicate predicate = criteriaBuilder.equal(root.get("status"), status);
//            predicates.add(predicate);
//        }
//
//
//        query.where(predicates.toArray(new Predicate[0]));
//        List<ProductOfferings> productOfferings = entityManager.createQuery(query).getResultList();
//        return productOfferings;
//    }

    @Override
    public  Page<ProductOfferings> getAll(Integer pageSize,Integer pageNumber,String sortBy,String sortStyle){
        if(pageSize == null || pageSize < 1){
            throw new RuntimeException("Truyen sai du lieu");
        }
        if(pageNumber == null || pageNumber < 1){
            pageNumber = 1;
        }
        List<String> sortFields = Arrays.asList("id","name","price");
        if(sortBy == null || !sortFields.contains(sortBy)){
           sortBy = "id";
        }
        if(sortStyle == null || (!sortStyle.equalsIgnoreCase("asc") && !sortStyle.equalsIgnoreCase("desc"))){
            sortStyle = "asc";
        }
//        List<ProductOfferings> productOfferings = productOfferingRepo.getAllByPage(pageSize,pageSize * (pageNumber - 1));
        Sort sort;
        if (sortStyle.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber - 1,pageSize,sort);
        Page<ProductOfferings> productOfferings = productOfferingRepo.findAll(pageable);
        return productOfferings;
    }
}

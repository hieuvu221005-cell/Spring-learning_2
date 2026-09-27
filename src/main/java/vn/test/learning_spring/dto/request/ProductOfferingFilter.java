package vn.test.learning_spring.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOfferingFilter {
    private String name;
    private Long minPrice;
    private Long maxPrice;
    private String color;
    private String status;
}

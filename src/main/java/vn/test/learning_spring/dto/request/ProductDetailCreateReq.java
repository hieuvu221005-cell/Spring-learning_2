package vn.test.learning_spring.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailCreateReq implements Serializable {
    private String feature;
    private Long power;
    private String brand;
    private Long weight;
    private String image;
    private String video;
}

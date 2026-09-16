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
public class ProductOfferingCreateReq implements Serializable {
    private String name;
    private Long price;
    private String color;
}

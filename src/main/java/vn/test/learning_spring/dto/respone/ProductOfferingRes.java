package vn.test.learning_spring.dto.respone;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOfferingRes implements Serializable {

    private Long id;

    private String name;

    private Long price;

    private String color;

    private  String status;
}

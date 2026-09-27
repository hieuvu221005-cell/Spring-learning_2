package vn.test.learning_spring.dto.request;


import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignProductDetailReq implements Serializable {
    @NonNull
    private Long productOfferingId;
    private List<Long> productDetailIds;
}

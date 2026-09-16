package vn.test.learning_spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.test.learning_spring.common.StatusEnum;

import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "product_offerings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOfferings implements Serializable {
    @Id
    @Column(name = "id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false,length = 255)
    private String name;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "color", length = 255)
    private String color;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;


    @OneToMany(mappedBy = "productOfferings")
    @JsonIgnore
    private List<ProductOfferingDetail> productOfferingDetails;
// quan he 1-1
//    @OneToOne(fetch = FetchType.EAGER)
//    quan he n-1
//    @ManyToOne
//    @JoinColumn(name = "detail_id",referencedColumnName = "id")
////    @JsonIgnore
//    private ProductDetail productDetail;

}

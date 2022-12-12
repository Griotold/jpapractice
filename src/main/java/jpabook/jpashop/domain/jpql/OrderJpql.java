package jpabook.jpashop.domain.jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderJpql {
    @Id @GeneratedValue
    private Long id;

    private int orderAmount;
    @Embedded
    private AddressJpql addressJpql;
    @ManyToOne
    @JoinColumn(name="PRODUCTJPQL_ID")
    private ProductJpql productJpql;
}

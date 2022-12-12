package jpabook.jpashop.domain.jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
public class AddressJpql {

    private String city;
    private String street;
    private String zipcode;
}

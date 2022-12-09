package jpabook.jpashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter // 세터를 지우고
@NoArgsConstructor 
@AllArgsConstructor // 생성자로 초기화 한후 불변 객체로
public class Address {
    private String city;
    private String street;
    private String zipcode;
}

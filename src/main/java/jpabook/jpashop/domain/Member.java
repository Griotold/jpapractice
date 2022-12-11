package jpabook.jpashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
public class Member {
    @Id
    @GeneratedValue // 기본값이 Auto
    @Column(name="member_id")
    private Long id;

    @Column(name="username")
    private String name;

    // 기간 : Period
    @Embedded
    private Period workPeriod;

    // 집주소 : Address
    @Embedded
    private Address homeAddress;

    // 직장 주소
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city",
            column=@Column(name="work_city")),
            @AttributeOverride(name="street",
            column=@Column(name="work_street")),
            @AttributeOverride(name="zipcode",
            column=@Column(name="work_zipcode"))
    })
    private Address workAddress;

    // 좋아하는 음식들
    @ElementCollection
    @CollectionTable(name = "favorite_food", joinColumns =
        @JoinColumn(name="member_id"))
    private Set<String> favoriteFood = new HashSet<>();

    // 주소 이력
    @ElementCollection
    @CollectionTable(name = "address_history", joinColumns =
        @JoinColumn(name="member_id"))
    private List<Address> addressHistory = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

}

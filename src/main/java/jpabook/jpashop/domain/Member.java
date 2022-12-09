package jpabook.jpashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name="locker_id")
//    private Locker locker;

//    @ManyToOne
//    @JoinColumn(name="team_id");
//    private Team team;
}

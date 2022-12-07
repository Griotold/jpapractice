package jpabook.jpashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
public class Member extends BaseEntity{
    @Id
    @GeneratedValue // 기본값이 Auto
    @Column(name="member_id")
    private Long id;

    @Column(name="username")
    private String name;

    private String city;
    private String street;
    private String zipcode;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name="locker_id")
//    private Locker locker;

//    @ManyToOne
//    @JoinColumn(name="team_id");
//    private Team team;
}

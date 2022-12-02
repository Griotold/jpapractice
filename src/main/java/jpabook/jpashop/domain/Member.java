package jpabook.jpashop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

//    private String city;
//    private String street;
//    private String zipcode;
}

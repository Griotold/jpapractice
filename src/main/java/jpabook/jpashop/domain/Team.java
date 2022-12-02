package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    @Column(name="team_name")
    private String name;
}

package jpabook.jpashop.domain.jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class MemberJpql {
    @Id @GeneratedValue
    private Long id;

    private String username;

    private int age;
    @ManyToOne
    @JoinColumn(name="TEAMJPQL_ID")
    private TeamJpql teamJpql;
}

package jpabook.jpashop.domain.jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class MemberJpql {
    @Id @GeneratedValue
    private Long id;

    private String username;

    private int age;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="teamJpql_id")
    private TeamJpql teamJpql;

    // 연관관계 편의 메소드
    public void changeTeam(TeamJpql teamJpql){
        this.teamJpql = teamJpql;
        teamJpql.getMemberJpqls().add(this);
    }
}

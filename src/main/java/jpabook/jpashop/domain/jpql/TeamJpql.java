package jpabook.jpashop.domain.jpql;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class TeamJpql {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "teamJpql")
    private List<MemberJpql> memberJpqls = new ArrayList<>();
}

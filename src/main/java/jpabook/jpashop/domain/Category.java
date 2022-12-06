package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id @GeneratedValue
    @Column(name="category_id")
    private Long id;

    private String name;

    @ManyToOne // 자식들 입장에서 부모는 하나
    @JoinColumn(name="parent_id")
    private Category parent;

    // 양방향
    @OneToMany(mappedBy = "parent")
    private List<Category> child;

    // 다대다 // 연결 테이블 만들어줘야함
    @ManyToMany
    @JoinTable(name="category_item",
            joinColumns = @JoinColumn(name="category_id"),
            inverseJoinColumns = @JoinColumn(name="item_id")
    )
    private List<Item> items = new ArrayList<>();
}

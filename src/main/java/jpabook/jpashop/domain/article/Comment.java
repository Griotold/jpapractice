package jpabook.jpashop.domain.article;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Comment {
    @Id
    @GeneratedValue
    @Column(name="comment_id")
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;
}

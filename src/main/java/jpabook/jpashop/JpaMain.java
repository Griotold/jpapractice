package jpabook.jpashop;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.article.Article;
import jpabook.jpashop.domain.article.Comment;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            Comment comment1 = new Comment();
            comment1.setContent("코멘트1");

            Comment comment2 = new Comment();
            comment2.setContent("코멘트2");

            Article article = new Article();
            article.setTitle("title1");
            article.setBody("고아객체 연습");
            article.addComment(comment1);
            article.addComment(comment2);
            em.persist(article);

            em.flush();
            em.clear();

            Article findArticle = em.find(Article.class, article.getId());
            findArticle.getComments().remove(0);


            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally{
            em.close();
        }
        emf.close();
    }
}

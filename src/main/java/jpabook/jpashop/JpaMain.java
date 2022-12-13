package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.article.Article;
import jpabook.jpashop.domain.article.Comment;
import jpabook.jpashop.domain.jpql.AddressJpql;
import jpabook.jpashop.domain.jpql.MemberDTO;
import jpabook.jpashop.domain.jpql.MemberJpql;


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

        // 프로젝션
        try{
            for(int i = 0; i<100;i++){
                MemberJpql memberJpql = new MemberJpql();
                memberJpql.setUsername("member"+ i);
                memberJpql.setAge(i);
                em.persist(memberJpql);
            }
            em.flush();
            em.clear();

            List<MemberJpql> resultList = em.createQuery("select m from MemberJpql m order by m.age desc"
                            , MemberJpql.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();
            System.out.println("resultList.size() = " + resultList.size());
            for (MemberJpql memberJpql : resultList) {
                System.out.println("memberJpql.getUsername() = " + memberJpql.getUsername());
                
            }

            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally{
            em.close();
        }
        emf.close();
    }
}

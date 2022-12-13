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

            MemberJpql memberJpql = new MemberJpql();
            memberJpql.setUsername("Griotold");
            memberJpql.setAge(10);
            em.persist(memberJpql);

            em.flush();
            em.clear();

            List<MemberDTO> memberDTOS = em.createQuery("select new jpabook.jpashop.domain.jpql.MemberDTO(m.username, m.age) from MemberJpql m"
                            , MemberDTO.class)
                    .getResultList();
            MemberDTO memberDTO = memberDTOS.get(0);
            System.out.println("memberDTO.username = " + memberDTO.getUsername());
            System.out.println("memberDTO.age = " + memberDTO.getAge());

            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally{
            em.close();
        }
        emf.close();
    }
}

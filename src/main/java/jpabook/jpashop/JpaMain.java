package jpabook.jpashop;

import jpabook.jpashop.domain.*;
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

        // 값 타입 컬렉션
        try{
            Member member = new Member();
            member.setName("Griotold");
            member.setHomeAddress(new Address("homeCity", "homeStreet", "12345"));

            member.getFavoriteFood().add("치킨");
            member.getFavoriteFood().add("족발");
            member.getFavoriteFood().add("피자");

            member.getAddressHistory()
                    .add(new Address("oldCity1", "oldStreet1", "23456"));
            member.getAddressHistory()
                    .add(new Address("oldCity2", "oldStreet2", "34567"));

            em.persist(member);

            em.flush();
            em.clear();
            // 치킨 -> 한식
            Member findMember = em.find(Member.class, member.getId());
            findMember.getFavoriteFood().remove("치킨");
            findMember.getFavoriteFood().add("한식");

            // oldCity1 -> newCity123
            findMember.getAddressHistory().
                    remove(new Address("oldCity1", "oldStreet1", "23456"));
            findMember.getAddressHistory().add(new Address("newCity123", "oldStreet1", "23456"));




            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally{
            em.close();
        }
        emf.close();
    }
}

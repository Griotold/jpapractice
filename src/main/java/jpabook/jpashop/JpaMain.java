package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.article.Article;
import jpabook.jpashop.domain.article.Comment;
import jpabook.jpashop.domain.jpql.AddressJpql;
import jpabook.jpashop.domain.jpql.MemberDTO;
import jpabook.jpashop.domain.jpql.MemberJpql;
import jpabook.jpashop.domain.jpql.TeamJpql;


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

        // 조인
        try{
            TeamJpql teamJpql = new TeamJpql();
            teamJpql.setName("teamA");
            em.persist(teamJpql);

            MemberJpql memberJpql = new MemberJpql();
            memberJpql.setUsername("member1");
            memberJpql.setAge(10);
            memberJpql.changeTeam(teamJpql);
            em.persist(memberJpql);

            em.flush();
            em.clear();

            String query = "select m from MemberJpql inner join m.teamjpql t";
            List<MemberJpql> resultList = em.createQuery(query, MemberJpql.class)
                    .getResultList();

            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally{
            em.close();
        }
        emf.close();
    }
}

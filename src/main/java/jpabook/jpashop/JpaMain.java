package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.article.Article;
import jpabook.jpashop.domain.article.Comment;
import jpabook.jpashop.domain.jpql.*;


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

        // 페치조인
        try{
            TeamJpql teamJpql1 = new TeamJpql();
            teamJpql1.setName("t1");
            em.persist(teamJpql1);

            TeamJpql teamJpql2 = new TeamJpql();
            teamJpql2.setName("t2");
            em.persist(teamJpql2);

            MemberJpql memberJpqlA = new MemberJpql();
            memberJpqlA.setUsername("mA");
            memberJpqlA.setTeamJpql(teamJpql1);
            em.persist(memberJpqlA);

            MemberJpql memberJpqlB = new MemberJpql();
            memberJpqlB.setUsername("mB");
            memberJpqlB.setTeamJpql(teamJpql1);
            em.persist(memberJpqlB);

            MemberJpql memberJpqlC = new MemberJpql();
            memberJpqlC.setUsername("mC");
            memberJpqlC.setTeamJpql(teamJpql2);
            em.persist(memberJpqlC);

            em.flush();
            em.clear();

            String query = "select t from Team";
            List<TeamJpql> teamList = em.createQuery(query, TeamJpql.class)
                    .setFirstResult(0)
                    .setMaxResults(2)
                    .getResultList();

            for (TeamJpql team : teamList) {
                System.out.println("team.getName() = " + team.getName()
                                + " , "+team.getMemberJpqls().size());

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

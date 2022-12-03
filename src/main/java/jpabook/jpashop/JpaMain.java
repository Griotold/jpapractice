package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        // 양방향 연관관계
        try{
            // 팀 저장
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            // 멤버 저장
            Member member = new Member();
            member.setName("memA");

            em.persist(member);
            // 연관관계 편의 메소드로 양쪽에 값 입력
            //member.changeTeam(team);
            team.addMember(member);

            // 데이터에 반영시키기
            em.flush();
            em.clear();

            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally{
            em.close();
        }
        emf.close();
    }
}

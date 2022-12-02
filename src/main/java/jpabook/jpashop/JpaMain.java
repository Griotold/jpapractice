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
            member.setTeam(team); //단방향 연관관계 설정, 참조 저장
            em.persist(member);

            // 데이터에 반영시키기
            em.flush();
            em.clear();

            //조회
            Team findTeam = em.find(Team.class, team.getId());
            int memberSize = findTeam.getMembers().size(); //역방향 조회
            System.out.println("memberSize = " + memberSize);
            List<Member> members = findTeam.getMembers();
            for (Member m : members) {
                System.out.println("m.getName() = " + m.getName());
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

package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Component scan에 의해 bean으로 등록된다.
@RequiredArgsConstructor
public class MemberRepository {

    // Spring에서 JPA는 @PersistenceContext -> @Autowired로 변경 가능하다.
    private final EntityManager em;

//    public MemberRepository(EntityManager em) {
//        this.em = em;
//    }

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList(); // JPQL, 객체를 대상으로 쿼리를 한다.
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}

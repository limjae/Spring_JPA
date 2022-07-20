package practice.jpa_basic.repository;

import org.springframework.stereotype.Repository;
import practice.jpa_basic.entity.EntityRule;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EntityRuleRepository {
    @PersistenceContext
    private EntityManager em;

    //    side effect를 고려하여 객체 자체를 반환하지 말자
    public Long save(EntityRule entityRule) {
        em.persist(entityRule);
        return entityRule.getId();
    }

    public EntityRule find(Long id){
        return em.find(EntityRule.class, id);
    }

    public List<EntityRule> findByUsernameJPQL(String username){
        String qlString = "select m FROM EntityRuleRename m where m.username like :name";
        TypedQuery<EntityRule> query = em.createQuery(qlString, EntityRule.class);
        query.setParameter("name", username);

        return query.getResultList();
    }

}

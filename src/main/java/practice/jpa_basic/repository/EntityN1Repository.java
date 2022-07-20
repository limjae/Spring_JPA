package practice.jpa_basic.repository;

import org.springframework.stereotype.Repository;
import practice.jpa_basic.entity.EntityN1;
import practice.jpa_basic.entity.EntityRule;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EntityN1Repository {
    @PersistenceContext
    private EntityManager em;

    //    side effect를 고려하여 객체 자체를 반환하지 말자
    public Long save(EntityN1 entityN1) {
        em.persist(entityN1);
        return entityN1.getId();
    }

    public EntityN1 find(Long id){
        return em.find(EntityN1.class, id);
    }

    public List<EntityN1> findByNameJPQL(String username){
        String qlString = "select m FROM EntityN1 m where m.name like :name";
        TypedQuery<EntityN1> query = em.createQuery(qlString, EntityN1.class);
        query.setParameter("name", username);
        return query.getResultList();
    }

    public List<EntityN1> findByNameFetchJPQL(String username){
        TypedQuery<EntityN1> query = em.createQuery("select distinct m FROM EntityN1 m join fetch m.entityRules r where m.name like :name", EntityN1.class);
        query.setParameter("name", username);
        return query.getResultList();
    }

}

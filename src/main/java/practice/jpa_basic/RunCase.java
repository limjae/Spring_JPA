package practice.jpa_basic;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import practice.jpa_basic.entity.EntityN1;
import practice.jpa_basic.entity.EntityRule;
import practice.jpa_basic.repository.EntityN1Repository;
import practice.jpa_basic.repository.EntityRuleRepository;

@Component
@RequiredArgsConstructor
public class RunCase {
    private final EntityRuleRepository entityRuleRepository;
    private final EntityN1Repository entityN1Repository;
    @Transactional
    public void init() {
        for (int i = 0; i < 5; i++) {
            EntityN1 entityN1 = new EntityN1();
            entityN1.setName("N1-" + i);
            entityN1Repository.save(entityN1);
            for (int j = 0; j < i; j++) {
                EntityRule entityRule = new EntityRule();
                entityRule.setAge(i + 1L);
                entityRule.setUsername("Hello" + i + "-" + j);
                entityRule.connectN1(entityN1);
                entityRuleRepository.save(entityRule);
            }
        }
    }

    @Transactional(readOnly = true)
    public void readN1() {
        // N+1 발생
        entityN1Repository.findByNameJPQL("N1-4")
                .forEach(s -> {
                    System.out.println("s.getName() = " + s.getName());
                    s.getEntityRules().forEach(r -> System.out.println("r.getUsername() = " + r.getUsername()));
                });
    }

    @Transactional(readOnly = true)
    public void readFetch() {
        // N+1이 발생하지 않음
        entityN1Repository.findByNameFetchJPQL("N1-4")
                .forEach(s -> {
                    System.out.println("s.getName() = " + s.getName());
                    s.getEntityRules().forEach(r -> System.out.println("r.getUsername() = " + r.getUsername()));
                });
    }

}

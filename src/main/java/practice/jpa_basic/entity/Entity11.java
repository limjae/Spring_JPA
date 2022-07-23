package practice.jpa_basic.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ENTITY_11")
public class Entity11 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ENTITY_11_ID")
    private Long id;

    // 외래키를 관리하지 않는 쪽은 참조하고 있는 Key가 없어 해당 객체가 Null인지 아닌지 프록시 객체가 알 방도가 없어서 LAZY가 적용되지 않음
    // ENTITY_11 참조시 ENTITY_11에 값이 없어서 ENTITYRULE을 불러와서 값이 있는지 없는지 무조건 검사해야함
    @OneToOne(mappedBy = "entity11", fetch = FetchType.LAZY)
    private EntityRule entityRule;

}
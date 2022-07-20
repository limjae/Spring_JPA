package practice.jpa_basic.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ENTITY_1N")
public class Entity1N {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ENTITY_1N_ID")
    private Long id;

    //    이렇게 할거면 그냥 다대일(N:1) 양방향으로 구현하자
    //    억지처럼 구현이 됨, 실제 DB에서는 EntityRule 테이블에서 FK관리가 되고 EntityRule에서 값을 참조해서 불러옴
    @OneToMany
    //    JoinColumn 안쓰면 이상한 맵핑 테이블이 하나 더 생김 (중요)
    @JoinColumn(name = "TEAM_ID")
    private List<EntityRule> entityRules = new ArrayList<>();
}

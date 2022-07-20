package practice.jpa_basic.entity.inheritMapping;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ITEMI")
//테이블 마다 하나씩 지정, 부모타입 Item Type으로 탐색할 경우 자식타입에서 전부 union으로 검색해버림(문제)
//쓰지않는 것을 추천
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

//JOINED 설정 시 테이블이 가장 크게 정규화됨 무결성 제약조건을 걸어서 맞춰줄수있음, 조회 쿼리가 단점
//JOINED 전략에서는 DTYPE이 필요 없을 수 있음
@Inheritance(strategy = InheritanceType.JOINED)

//탐색 쿼리가 간단해지고 조회성능이 좀 빨라짐, null 허용
//plain하게 설정하면 한 테이블에 다 떄려박기. Itemi 에 맵핑되어있음
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "DTYPE")
public abstract class Itemi {
    @Id @GeneratedValue
    private Long id;

    private String name;

}


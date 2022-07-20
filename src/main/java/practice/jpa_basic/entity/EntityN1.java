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
@Table(name = "ENTITY_N1")
public class EntityN1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ENTITY_N1_ID")
    private Long id;

    private String name;

    //    null point 방지 = new ArrayList<>();
    //    mappedBy에 연결되어 있는 쪽의 private EntityN1 entityN1; 중 변수명 entityN1을 등록
    //    mappedBy는 주인이 아닌 경우 사용함, 주인이 아닌쪽은 읽기만 허용하고 주인만이 외래키를 관리
    //    mappedBy의 의미 --> entityN1 에 의해 맵핑됨
    //    외래키가 있는 곳을 주인으로 설정하는 것을 권장
    //    단방향 매핑 만으로도 연관관계의 mapping은 끝남. 역방향 검색 기능만 추가됨.
    //    설계시에는 단방향으로 설계하고 역방향으로 참조 할 일이 생길 때 구현하는것을 추천
    //
    //    특정 엔티티를 영속 상태로 만들 때 연관된 엔티티도 함께 영속 상태로 만들고 싶을 떄
    //    ex) 부모 엔티티를 저장할 떄 자식도 함께 저장 하고 싶을 때
    //    parent가 있고, child1, child2, child3가 있으면 persist를 4번 호출 해야함. 근데 Parent 중심으로 코딩하여 1번만 호출하고 싶을 때
    //    @OneToMany에 cascade의 CASCADE = PERSIST, REMOVE, ALL을 적용
    //
    //    하나의 부모가 자원을 단독적으로 관리 할 때 (게시판의 첨부파일 등)
    //    단 이때 자식 child가 여러 부모에 의해 관리 될 경우 쓰면 안됨
    //
    //    고아객체 orphanRemoval
    //    부모 엔티티와 연관 관계가 끊어진 자식 엔티티를 자동으로 삭제
    //    parent.getChildren().remove(id)의 경우 해당 자식 엔티티를 자동으로 지워 줌
    //    orphanRemove 역시 하나의 부모가 자원을 단독적으로 관리 할 때 (게시판의 첨부파일 등)
    //    단 이때 자식 child가 여러 부모에 의해 관리 될 경우 쓰면 안됨
    @OneToMany(mappedBy = "entityN1", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntityRule> entityRules = new ArrayList<>();

}

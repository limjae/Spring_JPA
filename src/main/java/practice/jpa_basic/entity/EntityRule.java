package practice.jpa_basic.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

//@Entity가 붙은 객체에 대하여 관리함
//name은 JPA에서 사용할 이름을 정해줌, 기본값은 클래스 이름을 그대로 사용함
// 같은 클래스 명이 없는 경우 기본값을 사용해도 무방
@Entity(name = "EntityRuleRename")
// 해당 이름을 가진 테이블 명으로 DB에 쿼리가 호출
//@Table(name = "AAA", catalog = "BBB", schema = "CCC", uniqueConstraints={})
@Table(name = "EnRl")

//기본 생성자 public 또는 protected가 필요함
@NoArgsConstructor
//@SequenceGenerator는 @GeneratedValue(strategy = GenerationType.SEQUENCE)에 사용
//@SequenceGenerator(name = "SEQ_GEN", sequenceName = "MY_SEQ_NAME", initialValue = 1, allocationSize = 10)
//@TableGenerator(name = "SEQ_GEN", table = "MY_SEQ", initialValue = 1, allocationSize = 10)
@Getter
@Setter
public class EntityRule {
//    final enum interface innerclass 사용 불가
//    저장할 필드에 final 금지


    //  기본키 설정 @Id로 가능
//  직접 id를 할당하고 싶은 경우 GeneratedValue를 제외하고 @Id만을 사용한다
    @Id
//    IDENTITY : 데이터베이스에 키 생성을 위임, MYSQL상에서 AUTO_INCREMENT
//          Insert SQL 실행 이후 ID 값을 확인 가능. DB에서 값이 들어가봐야 암
//          em.persist()시에 예외적으로 SQL Insert 문이 출력 되고 1차 캐시에 ID를 반영함
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    SEQUENCE : 오라클과 같은 DB에서 자주 사용
//          Sequence Object를 생성한 뒤에 해당 값을 사용하여 ID
//          em.persist()시에 DB에서 값을 가져온 뒤에
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
//    TABLE : 어떤 DB에도 사용가능, 성능상에 문제가 생길 수 있음 운영상에서 사용하는걸 추천하지 않음
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SEQ_GEN")
//      Integer보단 Long을 추천 : 10억대 이상의 값을 다루는 경우 충돌이 발생 할 수 있음, 성능상 큰 차이가 발생하지 않음
//    권장 : Long형 + 대체키 + 키 생성 전략을 활용하자, 절대 비지니스 키를 사용하여 키를 쓰지 말자(요구사항 변경시 변경 비용이 너무 커진다)
//          DB에 지원하는 사항에 따라 선택하자
    private Long id;

    //    Column Annotation의 경우 DDL에 영향을 줌
//    nullable : SQL 문 과정 중에 해당 값이 null을 허용하는지 검사 및 제약 추가
//    DB Column 명은 name으로 지정됨
    @Column(nullable = true, length = 20, name = "name")
    private String username;

    //    BigInt 와 integer 와의 차이를 알 수 있음
//    insertable : SQL INSERT문에 해당 컬럼을 포함할지 여부
//    updatable : SQL UPDATE문에 해당 컬럼을 포함할지 여부
//    columnDefinition : 수동으로
    @Column(insertable = true, updatable = true)
//    @Column(columnDefinition = "columnDefinition = varchar(100)")
    private Long age;

    //    DB에서 Enum을 써야하는 경우
//    STRING의 경우 varchar로 입력됨
//    ORDINAL의 경우 ENUM의 순서를 입력(권장 x) 코드의 LOCKED를 입력 할 경우 0으로 저장함
//    ORDINAL의 경우 요구사항의 변경으로 ENUM 순서가 변경될 경우 답이 없어짐
//    데이터를 약간 더 추가하더라도 안정성을 위해 STRING으로 사용하자
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // DB에서의 시간 타입 DATE 날짜, TIME 시간, TIMESTAMP 날짜+시간
    // Java8이 LocalDate와 LocalDateTime을 쓸 경우 Temporal을 생략 가능하다
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    //    LocalDate는 날짜 정보만(DATE), LocalDateTime은 날짜와 시간정보가 포함된다(TIMESTAMP)
    private LocalDate timeTest1;
    private LocalDateTime timeTest2;

    //    VARCHAR를 넘어서는 자료를 넣어야하는 경우
//    지정할 수 있는 옵션이 없음
//    선언하는 값이 문자와 연관되었다면 clob, 그외의 경우 blob으로 저장됨
    @Lob
    private String description;
    @Lob
    private Byte[] description2;

    //   DB에 무관하게 사용하고 싶을 때, Mapping 생략
    @Transient
    private int temp;

    //    아래 타입의 경우 JPA에서 정의 해서 사용해야함
    //    임베디드 타입  = embedded, 복합값 타입
    //    근무 시작일과 근무 종료일을 클래스화 하면 어떨까?
    //    Embeddable 재사용 가능 선언
    //    Embedded 값을 소유한 엔티티 생명주기에 의존함
    //    Embeddable Embedded 하나만 써도 동작함
    //    테이블에 Period값이 내장되어있는 것을 볼 수 있다.
    //    코드 공통화의 장점이 있다.
    @Embedded
    private EmbeddedPeriod embeddedPeriod;

    //  Mapping 대신 연관 Key를 직접 다루는 방법(객체를 다루는 방식이 아님, 비추천)
    @Column(name = "ENTITY_N1_ID_FK")
    private Long entityN1ForeignKey;

    //    ManyToOne은 N:1관계임을 명시
    //    Member.getTeam은 가능하지만 반대가 불가능 할경우 단방향
    //    ~~ToOne의 경우 fetch = FetchType.LAZY를 적용해야 성능 개선하기 편하다
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENTITY_N1_ID")
    private EntityN1 entityN1;

    //  키 관리 주인
    //  주 테이블에 매핑시 null 허용하는 단점, 객체지향적으로 편함
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENTITY_11_ID")
    private Entity11 entity11;

    // 억지 양방향(1:N 비추천)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENTITY_1N_ID", insertable = false, updatable = false)
    private Entity1N entity1N;

    // 연관관계에 있는 객체들을 수정 시 수정 메소드를 구현해주는 것이 실수를 줄일 수 있다.
    public void connectN1(EntityN1 entityN1) {
        this.entityN1 = entityN1;
        entityN1.getEntityRules().add(this);
    }
}

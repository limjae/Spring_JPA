# 프록시?
프록시를 써야하는 이유?

Member를 조회할 때 Team까지 조회해야하는 경우?
또는 불러올 필요가 없는 경우?

em.getReference() = 데이터베이스에서 조회를 미루는 가짜 엔티티 객체 조회
실제 객체에 접근할 당시에 SQL문을 호출함

member - team(MANYTOONE) 정보를 가져올 때 eager로 설정 시 한번에 들고옴

member - team(MANYTOONE) 정보를 가져올 때 Lazy로 설정 시 프록시를 통해 객체를 참조 할 떄 다시 로딩함

그럼 Eager가 더 좋은것이 아닌가?

1.TEAM을 조회하지 않는 경우 조인 연산을 수행하게 되어 연산이 느림.

한두개까지는 괜찮은데 여러개를 참조시 문제가 발생함.

2.JPQL을 사용 할 경우 Eager로 설정된 모든 컬럼에 대하여 무조건 N+1이 발생함
 = 성능이 개판이 됨

<p>
N+1 최초 쿼리 1개에 의해 추가적으로 N개의 쿼리가 발생하는 현상<br>
ManyToOne, OneToOne은 기본이 즉시로딩을 설정되어 지연 로딩으로 설정하기를 권장(중요)<br>
실무에서는 전부 Lazy로 써라!!!!!<br>
</p>

해결법?<br>
1.전부 LAZY로 전환<br>
2-1. JPQL을 통해 join fetch를 사용<br>
2-2. Entity Graph<br>
2-3. batchsize로 해결<br>


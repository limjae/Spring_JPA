package practice.jpa_basic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
//CommandLineRunner 인터페이스를 구현하고 @Component annotaion을 사용하면 Compoent Scan 이후 구동시점에 run이 실행됩니다.
//https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/CommandLineRunner.html
public class AppRunner implements CommandLineRunner {
    private final RunCase runCase;

    @Override
    public void run(String... args) throws Exception {
        runCase.init();
        runCase.readN1();
        runCase.readFetch();
    }




}
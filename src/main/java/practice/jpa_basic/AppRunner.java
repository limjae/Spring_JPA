package practice.jpa_basic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {
    private final RunCase runCase;

    @Override
    public void run(String... args) throws Exception {
        runCase.init();
        runCase.readN1();
        runCase.readFetch();
    }




}
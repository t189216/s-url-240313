package com.ll.surl240313.global.initData;

import com.ll.surl240313.domain.surl.surl.service.SurlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class NotProd {
    private final SurlService surlService;

    @Bean
    public ApplicationRunner initNotProd() {
        return args -> {
            if (surlService.count() > 0) return;

            log.debug("initNotProd");
        };
    }
}

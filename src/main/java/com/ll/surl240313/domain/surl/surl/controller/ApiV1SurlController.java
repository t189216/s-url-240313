package com.ll.surl240313.domain.surl.surl.controller;

import com.ll.surl240313.domain.surl.surl.service.SurlService;
import com.ll.surl240313.global.rq.Rq;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/surls")
@RequiredArgsConstructor
@Slf4j
public class ApiV1SurlController {
    private final SurlService surlService;
    private final Rq rq;

    @Data
    public static class SurlCreateReqBody {
        @NotBlank
        public String url;
        public String title;
    }

    @PostMapping("")
    @PreAuthorize("isAuthenticated()")
    @Transactional
    public void create(
            @Valid @RequestBody SurlCreateReqBody reqBody
    ) {
        surlService.create(rq.getMember(), reqBody.url, reqBody.title);
    }

    @Data
    public static class SurlModifyReqBody {
        @NotBlank
        public String title;
    }

    @PutMapping("/{id}")
    public void modify(
            @PathVariable long id,
            @Valid @RequestBody SurlModifyReqBody reqBody
    ) {
        surlService.modify(id, reqBody.title);
    }
}
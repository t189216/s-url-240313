package com.ll.surl240313.domain.surl.surl.controller;

import com.ll.surl240313.domain.surl.surl.service.SurlService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/surls")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApiV1SurlController {
    private final SurlService surlService;

    @Data
    public static class SurlCreateReqBody {
        public String url;

        @NotBlank
        public String title;
    }

    @PostMapping("")
    @Transactional
    public void create(
            @Valid @RequestBody SurlCreateReqBody reqBody
    ) {
        surlService.create(reqBody.url, reqBody.title);
    }
}
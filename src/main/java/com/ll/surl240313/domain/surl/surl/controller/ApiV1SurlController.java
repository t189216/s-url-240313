package com.ll.surl240313.domain.surl.surl.controller;

import com.ll.surl240313.domain.surl.surl.service.SurlService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/surls")
@RequiredArgsConstructor
public class ApiV1SurlController {
    private final SurlService surlService;

    @Data
    public static class SurlCreateReqBody {
        @NotBlank
        public String url;
        public String title;
    }

    @PostMapping("")
    public void create(
            @Valid @RequestBody SurlCreateReqBody reqBody
    ) {
        surlService.create(reqBody.url, reqBody.title);
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
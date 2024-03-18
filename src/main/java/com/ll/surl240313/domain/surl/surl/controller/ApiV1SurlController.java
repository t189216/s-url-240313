package com.ll.surl240313.domain.surl.surl.controller;

import com.ll.surl240313.domain.member.member.entity.Member;
import com.ll.surl240313.domain.member.member.service.MemberService;
import com.ll.surl240313.domain.surl.surl.service.SurlService;
import com.ll.surl240313.global.security.SecurityUser;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/surls")
@RequiredArgsConstructor
@Slf4j
public class ApiV1SurlController {
    private final SurlService surlService;
    private final MemberService memberService;

    @Data
    public static class SurlCreateReqBody {
        @NotBlank
        public String url;
        public String title;
    }

    @PostMapping("")
    public void create(
            @Valid @RequestBody SurlCreateReqBody reqBody,
            Principal principal
    ) {
        SecurityUser user = principal == null ? null :  (SecurityUser) ((Authentication) principal).getPrincipal();
        log.debug("principal: {}", principal);
        Member author = memberService.findById(4L).get();
        surlService.create(author, reqBody.url, reqBody.title);
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
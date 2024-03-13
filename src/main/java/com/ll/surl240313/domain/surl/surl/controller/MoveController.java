package com.ll.surl240313.domain.surl.surl.controller;

import com.ll.surl240313.domain.surl.surl.entity.Surl;
import com.ll.surl240313.domain.surl.surl.service.SurlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class MoveController {
    private final SurlService surlService;

    @GetMapping("/{id:\\d+}")
    public String move(
            @PathVariable long id
    ) {
        Surl surl = surlService.findById(id).get();

        return "redirect:" + surl.getUrl();
    }
}
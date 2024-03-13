package com.ll.surl240313.domain.home.home.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
package com.ll.surl240313.global.rq;

import com.ll.surl240313.domain.member.member.entity.Member;
import com.ll.surl240313.domain.member.member.service.MemberService;
import com.ll.surl240313.global.app.AppConfig;
import com.ll.surl240313.global.security.SecurityUser;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Optional;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final MemberService memberService;
    private final HttpServletRequest req;
    private final HttpServletResponse resp;

    // 캐시 데이터
    private SecurityUser securityUser;
    private Member member;
    private Boolean isLogin;

    public Member getMember() {
        if (isLogout()) return null;

        if (member == null) member = memberService.getReferenceById(getSecurityUser().getId());

        return member;
    }

    private SecurityUser getSecurityUser() {
        if (securityUser != null) return securityUser;

        securityUser = Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(authentication -> authentication.getPrincipal() instanceof SecurityUser)
                .map(authentication -> (SecurityUser) authentication.getPrincipal())
                .orElse(null);

        isLogin = securityUser != null;

        return securityUser;
    }

    public boolean isLogout() {
        return !isLogin();
    }

    public boolean isLogin() {
        if (isLogin == null) getSecurityUser();

        return isLogin;
    }

    public String getCurrentUrlPath() {
        return req.getRequestURI();
    }

    public void setStatusCode(int statusCode) {
        resp.setStatus(statusCode);
    }

    public void setCrossDomainCookie(String name, String value) {
        ResponseCookie cookie = ResponseCookie.from(name, value)
                .path("/")
                .domain(getSiteCookieDomain())
                .sameSite(AppConfig.isProd() ? "Strict" : "None")
                .secure(true)
                .httpOnly(true)
                .build();

        resp.addHeader("Set-Cookie", cookie.toString());
    }

    private String getSiteCookieDomain() {
        String cookieDomain = AppConfig.getSiteCookieDomain();

        if (!cookieDomain.equals("localhost")) {
            return cookieDomain = "." + cookieDomain;
        }

        return null;
    }

    public String getHeader(String name, String defaultValue) {
        String value = req.getHeader(name);

        return value != null ? value : defaultValue;
    }

    public void setHeader(String name, String value) {
        resp.setHeader(name, value);
    }

    public void setLogin(SecurityUser securityUser) {
        SecurityContextHolder.getContext().setAuthentication(securityUser.genAuthentication());
    }

    public Cookie getCookie(String name) {
        Cookie[] cookies = req.getCookies();

        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }

        return null;
    }

    public String getCookieValue(String name, String defaultValue) {
        Cookie cookie = getCookie(name);

        if (cookie == null) {
            return defaultValue;
        }

        return cookie.getValue();
    }

    private long getCookieAsLong(String name, int defaultValue) {
        String value = getCookieValue(name, null);

        if (value == null) {
            return defaultValue;
        }

        return Long.parseLong(value);
    }
}
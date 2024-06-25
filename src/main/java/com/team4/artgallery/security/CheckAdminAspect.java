package com.team4.artgallery.security;

import com.team4.artgallery.security.exception.NotAdminException;
import com.team4.artgallery.service.MemberService;
import com.team4.artgallery.service.SessionService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CheckAdminAspect {

    private final MemberService memberService;
    private final SessionService sessionService;

    @Before("@annotation(com.team4.artgallery.annotation.CheckAdmin)")
    public void checkAdmin() {
        // 세션이 없거나 관리자가 아닌 경우 예외 발생
        HttpSession session = sessionService.getSession();
        if (session == null || !memberService.isAdmin(session)) {
            throw new NotAdminException();
        }
    }

}
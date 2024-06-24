package com.team4.artgallery.controller.admin;

import com.team4.artgallery.service.MemberService;
import com.team4.artgallery.util.ajax.ResponseHelper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/qna")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AdminQnaController {

    private final MemberService memberService;

    @Delegate
    private final ResponseHelper responseHelper;

    @GetMapping({"", "/"})
    public String list(HttpSession session) {
        // 관리자가 아닌 경우 404 페이지로 포워딩
        if (!memberService.isAdmin(session)) {
            System.out.println("관리자가 아닙니다");
            return "util/404";
        }

        return "admin/adminQnaList";
    }

}
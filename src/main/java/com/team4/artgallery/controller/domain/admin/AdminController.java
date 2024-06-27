package com.team4.artgallery.controller.domain.admin;

import com.team4.artgallery.aspect.annotation.CheckAdmin;
import com.team4.artgallery.service.AdminService;
import com.team4.artgallery.service.helper.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/admin")
@CheckAdmin
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @Delegate
    private final ResponseService responseHelper;

    @GetMapping({"", "/"})
    public String root() {
        return "admin/adminMain";
    }

    @PostMapping("/reset")
    public ResponseEntity<?> reset() {
        // 데이터베이스 초기화 시도
        try {
            adminService.resetDatabase();
        } catch (Exception e) {
            // 오류가 발생한 경우 실패 응답 반환
            System.out.println(Arrays.toString(e.getStackTrace()));
            return internalServerError("데이터베이스 초기화에 실패했습니다.");
        }

        // 데이터베이스 초기화에 성공한 경우 성공 응답 반환
        return ok("데이터베이스가 초기화되었습니다.");
    }

}
package com.team4.artgallery.controller;

import com.team4.artgallery.dto.QnaDto;
import com.team4.artgallery.service.MemberService;
import com.team4.artgallery.service.QnaService;
import com.team4.artgallery.util.Pagination;
import com.team4.artgallery.util.ajax.ResponseHelper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class QnaController {

    private final QnaService qnaService;

    private final MemberService memberService;

    @Delegate
    private final ResponseHelper responseHelper;

    @GetMapping("")
    public String list(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        Pagination pagination = new Pagination()
                .setCurrentPage(page)
                .setItemCount(qnaService.getAllCount())
                .setUrlTemplate("/qna?page=%d");
        model.addAttribute("pagination", pagination);
        model.addAttribute("qnaList", qnaService.getAll(pagination));
        return "qna/qnaList";
    }

    @GetMapping("/view")
    public String view(@RequestParam(value = "qseq") int qseq, Model model, HttpSession session) {
        // 문의글 정보가 없는 경우 404 페이지로 이동
        QnaDto qnaDto = qnaService.get(qseq);
        if (qnaDto == null) {
            return "util/404";
        }

        // 접근 권한이 없는 경우 오류 메시지 출력
        if (!qnaService.authorizeForRestrict(session, qseq)) {
            model.addAttribute("message", "잘못된 접근입니다.");
            return "util/alert";
        }

        // 문의글 조회 페이지로 이동
        model.addAttribute("qnaDto", qnaService.get(qseq));
        return "qna/qnaView";
    }

    @GetMapping("/write")
    public String write() {
        return "qna/qnaForm";
    }

    @PostMapping("/write")
    public ResponseEntity<?> write(@ModelAttribute QnaDto qnaDto) {
        // 문의글 작성에 실패한 경우 500 에러 반환
        if (qnaService.insert(qnaDto) == 0) {
            return internalServerError("문의 작성에 실패했습니다.");
        }

        // 문의글 작성에 성공한 경우 200 성공 반환
        return ok("문의 작성이 완료되었습니다.", "/qna/view?qseq=" + qnaDto.getQseq());
    }

    @GetMapping("/update")
    public String update(@RequestParam(value = "qseq") int qseq, Model model, HttpSession session) {
        // 문의글 정보가 없는 경우 404 페이지로 이동
        QnaDto qnaDto = qnaService.get(qseq);
        if (qnaDto == null) {
            return "util/404";
        }

        // 접근 권한이 없는 경우 오류 메시지 출력
        if (!qnaService.authorizeForPersonal(session, qseq)) {
            model.addAttribute("message", "잘못된 접근입니다.");
            return "util/alert";
        }

        // 문의글 수정 페이지로 이동
        model.addAttribute("qnaDto", qnaService.get(qseq));
        return "qna/qnaForm";
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@ModelAttribute QnaDto qnaDto, HttpSession session) {
        int qseq = qnaDto.getQseq();

        // 문의글 정보가 없는 경우 404 실패 반환
        if (qnaService.get(qseq) == null) {
            return notFound("문의글 정보를 찾을 수 없습니다.", "/qna");
        }

        // 접근 권한이 없는 경우 403 실패 반환
        if (!qnaService.authorizeForPersonal(session, qseq)) {
            return forbidden("접근 권한이 없습니다.");
        }

        // 문의글 수정에 실패한 경우 500 에러 반환
        if (qnaService.update(qnaDto) == 0) {
            return internalServerError("문의 수정에 실패했습니다.");
        }

        // 문의글 수정에 성공한 경우 200 성공 반환
        return ok("문의 수정이 완료되었습니다.", "/qna/view?qseq=" + qseq);
    }

    @PostMapping("/reply")
    public ResponseEntity<?> reply(
            @RequestParam(value = "qseq") int qseq,
            @RequestParam(value = "reply") String reply,
            HttpSession session
    ) {
        // 문의글 정보가 없는 경우 404 실패 반환
        if (qnaService.get(qseq) == null) {
            return notFound("문의글 정보를 찾을 수 없습니다.", "/qna");
        }

        // 접근 권한이 없는 경우 403 실패 반환
        if (!memberService.isAdmin(session)) {
            return forbidden("접근 권한이 없습니다.");
        }

        // 문의글 답변에 실패한 경우 500 에러 반환
        if (qnaService.reply(qseq, reply) == 0) {
            return internalServerError("문의 답변에 실패했습니다.");
        }

        // 문의글 답변에 성공한 경우 200 성공 반환
        return ok("문의 답변이 완료되었습니다.");
    }

    @PostMapping("/authorize")
    public ResponseEntity<?> authorize(
            @RequestParam(value = "qseq") int qseq,
            @RequestParam(value = "mode") String mode,
            @RequestParam(value = "pwd", required = false) String pwd,
            Model model,
            HttpSession session
    ) {
        switch (mode) {
            case "view":
                if (qnaService.authorizeForRestrict(session, qseq)) {
                    return ok("", "/qna/view?qseq=" + qseq);
                }
                break;
            case "delete":
                if (qnaService.authorizeForPrivilege(session, qseq)) {
                    if (qnaService.delete(qseq) == 0) {
                        return internalServerError("문의글 삭제에 실패했습니다.");
                    }

                    return ok("문의글이 삭제되었습니다.", "/qna");
                }
                break;
            case "update":
                if (qnaService.authorizeForPersonal(session, qseq)) {
                    return ok("", "/qna/update?qseq=" + qseq);
                }
                break;
            default:
                return badRequest("잘못된 요청입니다.");
        }

        if (pwd == null || pwd.trim().isEmpty()) {
            model.addAttribute("qseq", qseq);
            return unauthorized("비밀번호를 입력해주세요.");
        }

        if (!qnaService.authorizeWithPwd(session, qseq, pwd)) {
            return badRequest("비밀번호가 일치하지 않습니다.");
        }

        return authorize(qseq, mode, null, model, session);
    }

}

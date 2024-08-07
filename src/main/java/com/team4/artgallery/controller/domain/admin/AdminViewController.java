package com.team4.artgallery.controller.domain.admin;

import com.team4.artgallery.aspect.annotation.CheckAdmin;
import com.team4.artgallery.controller.exception.SqlException;
import com.team4.artgallery.dto.filter.ArtworkFilter;
import com.team4.artgallery.dto.filter.KeywordFilter;
import com.team4.artgallery.dto.filter.NoticeFilter;
import com.team4.artgallery.dto.filter.QnaFilter;
import com.team4.artgallery.service.*;
import com.team4.artgallery.util.Pagination;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@CheckAdmin
public class AdminViewController {

    private final ArtworkService artworkService;
    private final GalleryService galleryService;
    private final MemberService memberService;
    private final NoticeService noticeService;
    private final QnaService qnaService;

    // TODO: 책임 분리 혹은 AJAX 처리로 AdminViewController 클래스의 서비스 의존성 제거
    public AdminViewController(
            ArtworkService artworkService,
            GalleryService galleryService,
            MemberService memberService,
            NoticeService noticeService,
            QnaService qnaService
    ) {
        this.artworkService = artworkService;
        this.galleryService = galleryService;
        this.memberService = memberService;
        this.noticeService = noticeService;
        this.qnaService = qnaService;
    }

    @GetMapping
    public String root() {
        return "admin/adminMain";
    }

    @GetMapping("artwork")
    public String artwork(
            @Valid
            @ModelAttribute("filter")
            ArtworkFilter filter,
            @Valid
            @ModelAttribute("pagination")
            Pagination pagination,

            Model model
    ) {
        model.addAttribute("artworkList", artworkService.getArtworks(filter, pagination).toList());
        return "admin/adminArtworkList";
    }

    @GetMapping("gallery")
    public String gallery(
            @Valid
            @ModelAttribute("filter")
            KeywordFilter filter,
            @Valid
            @ModelAttribute("pagination")
            Pagination pagination,

            Model model
    ) {
        model.addAttribute("galleryList", galleryService.getGalleries(filter, pagination).toList());
        return "admin/adminGalleryList";
    }

    @GetMapping("member")
    public String member(
            @Valid
            @ModelAttribute("filter")
            KeywordFilter filter,
            @Valid
            @ModelAttribute("pagination")
            Pagination pagination,

            Model model
    ) {
        pagination
                .setUrlTemplateFromFilter(filter)
                .setItemCount(memberService.countMembers(filter));

        model.addAttribute("memberList", memberService.getMembers(filter, pagination).toList());
        return "admin/adminMemberList";
    }

    @GetMapping("notice")
    public String notice(
            @Valid
            @ModelAttribute("filter")
            NoticeFilter filter,
            @Valid
            @ModelAttribute("pagination")
            Pagination pagination,

            Model model
    ) {
        model.addAttribute("noticeList", noticeService.getNotices(filter, pagination).toList());
        return "admin/adminNoticeList";
    }

    @GetMapping("qna")
    public String qna(
            @Valid
            @ModelAttribute("filter")
            QnaFilter filter,
            @Valid
            @ModelAttribute("pagination")
            Pagination pagination,

            Model model
    ) throws SqlException {
        model.addAttribute("qnaList", qnaService.getInquiries(filter, pagination).toList());
        return "admin/adminQnaList";
    }

}

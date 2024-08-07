package com.team4.artgallery.controller.domain.gallery;

import com.team4.artgallery.aspect.annotation.CheckLogin;
import com.team4.artgallery.controller.exception.NotFoundException;
import com.team4.artgallery.controller.resolver.annotation.LoginMember;
import com.team4.artgallery.dto.filter.KeywordFilter;
import com.team4.artgallery.entity.MemberEntity;
import com.team4.artgallery.service.GalleryService;
import com.team4.artgallery.util.Pagination;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/gallery", produces = MediaType.TEXT_HTML_VALUE)
public class GalleryViewController {

    private final GalleryService galleryService;

    public GalleryViewController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping
    public String root(
            @Valid
            @ModelAttribute("filter")
            KeywordFilter filter,
            @Valid
            @ModelAttribute("pagination")
            Pagination pagination,

            Model model
    ) {
        model.addAttribute(
                "galleryList",
                galleryService.getGalleries(filter, pagination).toList()
        );
        return "gallery/galleryList";
    }

    @GetMapping("{gseq}")
    public String view(
            @PathVariable(name = "gseq")
            Integer gseq,

            Model model
    ) {
        galleryService.increaseReadCountIfNew(gseq);
        model.addAttribute("galleryEntity", galleryService.getGallery(gseq));
        return "gallery/galleryView";
    }

    @CheckLogin("/gallery/write?gseq=${gseq}")
    @GetMapping("write")
    public String write(
            @RequestParam(name = "gseq", required = false)
            Integer gseq,

            @LoginMember
            MemberEntity loginMember,
            Model model
    ) throws NotFoundException {
        if (gseq != null) {
            model.addAttribute("galleryEntity", galleryService.getGalleryOnlyAuthor(gseq, loginMember));
        }
        return "gallery/galleryWrite";
    }

}

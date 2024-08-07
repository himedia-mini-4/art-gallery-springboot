package com.team4.artgallery.controller.domain.artwork;

import com.team4.artgallery.aspect.annotation.CheckAdmin;
import com.team4.artgallery.controller.exception.NotFoundException;
import com.team4.artgallery.controller.resolver.annotation.LoginMember;
import com.team4.artgallery.dto.filter.ArtworkFilter;
import com.team4.artgallery.entity.MemberEntity;
import com.team4.artgallery.service.ArtworkService;
import com.team4.artgallery.util.Pagination;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/artwork", produces = MediaType.TEXT_HTML_VALUE)
public class ArtworkViewController {

    private final ArtworkService artworkService;

    public ArtworkViewController(ArtworkService artworkService) {
        this.artworkService = artworkService;
    }

    @GetMapping
    public String root(
            @Validated(ArtworkFilter.ExcludeDisplay.class)
            @ModelAttribute("filter")
            ArtworkFilter filter,
            @Valid
            @ModelAttribute("pagination")
            Pagination pagination,

            Model model
    ) {
        model.addAttribute(
                "artworkList",
                artworkService.getArtworks(filter.setDisplayyn('Y').setIncludeDisplay(false), pagination).toList()
        );
        return "artwork/artworkList";
    }

    @GetMapping("{aseq}")
    public String view(
            @PathVariable("aseq")
            Integer aseq,

            @LoginMember
            MemberEntity loginMember,
            Model model
    ) throws NotFoundException {
        model.addAttribute("artworkEntity", artworkService.getArtwork(aseq, loginMember));
        return "artwork/artworkView";
    }

    @CheckAdmin
    @GetMapping("write")
    public String write(
            @RequestParam(name = "aseq", required = false)
            Integer aseq,

            @LoginMember
            MemberEntity loginMember,
            Model model
    ) throws NotFoundException {
        if (aseq != null) {
            model.addAttribute("artworkEntity", artworkService.getArtwork(aseq, loginMember));
        }
        return "artwork/artworkWrite";
    }

}

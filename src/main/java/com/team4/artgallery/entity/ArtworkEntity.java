package com.team4.artgallery.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.team4.artgallery.dto.view.Views;
import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Table(name = "artwork")
@Comment("예술품")
@DynamicInsert
@DynamicUpdate
@Builder
public record ArtworkEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "aseq")
        @Comment("예술품 번호")
        Integer aseq,

        @Column(name = "name", length = 45, nullable = false)
        @Comment("이름")
        String name,

        @Column(name = "category", length = 45, nullable = false)
        @Comment("카테고리")
        String category,

        @Column(name = "artist", length = 45, nullable = false)
        @Comment("작가")
        String artist,

        @Column(name = "year", length = 4, nullable = false)
        @Comment("제작년도")
        String year,

        @Column(name = "material", length = 45, nullable = false)
        @Comment("재질")
        String material,

        @Column(name = "size", length = 45, nullable = false)
        @Comment("크기")
        String size,

        @Column(name = "display", length = 1, nullable = false)
        @Comment("전시여부")
        Boolean display,

        @Column(name = "content", nullable = false)
        @Comment("설명")
        String content,

        @Column(name = "image", length = 200, nullable = false)
        @Comment("저장된 파일명")
        String imageFileName,

        @Column(name = "indate", nullable = false)
        @ColumnDefault("NOW()")
        @Comment("등록일")
        LocalDateTime indate
) {

    /**
     * 사용자가 접근 가능한 이미지 경로를 반환합니다.
     */
    @JsonView({Views.Summary.class, Views.Detail.class})
    public String getImageSrc() {
        if (imageFileName.startsWith("http")) {
            return imageFileName;
        }

        return "/static/image/artwork/" + imageFileName;
    }

}
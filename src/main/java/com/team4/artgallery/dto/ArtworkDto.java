package com.team4.artgallery.dto;

import com.team4.artgallery.dto.enums.ArtworkCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class ArtworkDto {

    @Getter
    @Setter
    private Integer aseq;

    @Getter
    @Setter
    @NotBlank(message = "작품명을 입력해주세요.")
    @Size(max = 45, message = "작품명은 45자 이내로 입력해주세요.")
    private String name;

    /**
     * {@link ArtworkCategory#validValues()}
     */
    @Getter
    @Setter
    @NotBlank(message = "부문을 입력해주세요.")
    @Size(max = 45, message = "부문은 45자 이내로 입력해주세요.")
    @Pattern(regexp = "^회화|드로잉|판화|조각ㆍ설치|사진|공예|디자인|서예$", message = "부문은 회화, 드로잉, 판화, 조각ㆍ설치, 사진, 공예, 디자인, 서예 중 하나로 입력해주세요.")
    private String category;

    @Getter
    @Setter
    @NotBlank(message = "작가명을 입력해주세요.")
    @Size(max = 45, message = "작가명은 45자 이내로 입력해주세요.")
    private String artist;

    @Getter
    @Setter
    @NotBlank(message = "제작년도를 입력해주세요.")
    @Size(max = 4, message = "제작년도는 4자 이내로 입력해주세요.")
    private String year;

    @Getter
    @Setter
    @NotBlank(message = "재료를 입력해주세요.")
    @Size(max = 45, message = "재료는 45자 이내로 입력해주세요.")
    private String material;

    @Getter
    @Setter
    @NotBlank(message = "규격을 입력해주세요.")
    @Size(max = 45, message = "규격은 45자 이내로 입력해주세요.")
    private String size;

    @Getter
    @Setter
    @NotBlank(message = "전시상태를 입력해주세요.")
    @Size(max = 1, message = "전시상태는 1자 이내로 입력해주세요.")
    @Pattern(regexp = "^[YN]$", message = "전시상태는 Y 또는 N으로 입력해주세요.")
    private String displayyn;

    @Getter
    @Setter
    @NotBlank(message = "작품설명을 입력해주세요.")
    private String content;

    @Getter
    @Setter
    @Null(message = "이미지는 직접 설정할 수 없습니다.")
    private String image;

    @Getter
    @Setter
    @Null(message = "저장된 파일명은 직접 설정할 수 없습니다.")
    private String savefilename;

    @Getter
    @Null(message = "등록일은 직접 설정할 수 없습니다.")
    private Date indate;

    /**
     * 사용자가 접근 가능한 이미지 경로를 반환합니다.
     */
    public String getImageSrc() {
        if (savefilename.startsWith("http")) {
            return savefilename;
        }

        return "/static/image/artwork/" + savefilename;
    }

    public boolean isDisplay() {
        return displayyn.equals("Y");
    }

    public void setDisplay(boolean isDisplay) {
        displayyn = isDisplay ? "Y" : "N";
    }

}

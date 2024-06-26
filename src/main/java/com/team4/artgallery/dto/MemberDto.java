package com.team4.artgallery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    /**
     * 회원 아이디
     */
    @NotBlank(groups = OnLogin.class, message = "아이디는 필수 입력값입니다.")
    @Size(groups = OnLogin.class, min = 4, max = 45, message = "아이디는 4자 이상 45자 이하로 입력해주세요.")
    private String id;

    @NotBlank(groups = OnUpdate.class, message = "이름은 필수 입력값입니다.")
    @Size(groups = OnUpdate.class, min = 2, max = 45, message = "이름은 2자 이상 45자 이하로 입력해주세요.")
    private String name;

    @NotBlank(groups = OnLogin.class, message = "비밀번호는 필수 입력값입니다.")
    @Size(groups = OnLogin.class, min = 4, max = 45, message = "비밀번호는 4자 이상 45자 이하로 입력해주세요.")
    private String pwd;

    @NotBlank(groups = OnUpdate.class, message = "이메일은 필수 입력값입니다.")
    @Size(groups = OnUpdate.class, min = 4, max = 45, message = "이메일은 4자 이상 45자 이하로 입력해주세요.")
    private String email;

    @NotBlank(groups = OnUpdate.class, message = "전화번호는 필수 입력값입니다.")
    @Size(groups = OnUpdate.class, min = 4, max = 45, message = "전화번호는 4자 이상 45자 이하로 입력해주세요.")
    private String phone;

    @NotBlank(groups = OnUpdate.class, message = "주소는 필수 입력값입니다.")
    @Size(groups = OnUpdate.class, max = 100, message = "주소는 100자 이하로 입력해주세요.")
    private String address;

    @Null(message = "가입일은 직접 설정할 수 없습니다.")
    private Date indate;

    @Null(message = "관리자 여부는 직접 설정할 수 없습니다.")
    private String adminyn;

    public boolean isAdmin() {
        return adminyn.equals("Y");
    }

    public void setAdmin(boolean isAdmin) {
        adminyn = isAdmin ? "Y" : "N";
    }


    // 그룹 클래스

    /**
     * 회원 가입 요청 시 사용하는 그룹
     * <p>
     * 필수 요소 : {@link #id}, {@link #name}, {@link #pwd}, {@link #email}, {@link #phone}, {@link #address}
     */
    public interface OnJoin {
    }

    /**
     * 정보 수정 요청 시 사용하는 그룹
     * <p>
     * 필수 요소 : {@link #name}, {@link #email}, {@link #phone}, {@link #address}
     */
    public interface OnUpdate extends OnJoin {
    }

    /**
     * 로그인 요청 시 사용하는 그룹
     * <p>
     * 필수 요소 : {@link #id}, {@link #pwd}
     */
    public interface OnLogin extends OnJoin {
    }

}

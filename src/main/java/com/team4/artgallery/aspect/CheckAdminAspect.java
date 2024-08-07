package com.team4.artgallery.aspect;

import com.team4.artgallery.aspect.annotation.CheckAdmin;
import com.team4.artgallery.aspect.exception.NotAdminException;
import com.team4.artgallery.service.MemberService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 관리자 권한을 확인하는 Aspect 클래스
 *
 * @apiNote {@link CheckAdmin} 어노테이션이 붙은 메서드나 클래스의 메소드가 호출될 때 관리자 권한을 확인합니다.
 * <p>
 * 세션 정보가 없거나 관리자가 아닌 경우 {@link NotAdminException} 예외를 발생시킵니다.
 */
@Aspect
@Component
public class CheckAdminAspect {

    private final MemberService memberService;

    public CheckAdminAspect(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * {@code @annotation}을 통해 메서드에 붙은 {@link CheckAdmin} 어노테이션을 받아
     * {@link #checkAdmin(CheckAdmin)} 메서드에 전달합니다.
     *
     * @implNote {@link Before} 어노테이션으로 메서드 실행 전에 로직을 추가할 수 있습니다.
     * <p>
     * {@code value}값의 {@code @annotation} 은 클래스에 붙은 어노테이션을 찾아내는데 사용됩니다.
     * <p>
     * <blockquote><pre>@Before(value = "@annotation(com.team4.artgallery.aspect.annotation.CheckAdmin)")</pre></blockquote>
     * <p>
     * 위와 같은 방식으로 직접 클래스를 작성해도 되지만, "어노테이션 매개변수" 방식을 통해 사용할 수 있습니다.
     * 이 경우 해당 어노테이션 매개변수의 이름을 클래스 대신 사용하면 됩니다.
     */
    @Before("@annotation(annotation)")
    public void onAnnotation(CheckAdmin annotation) {
        checkAdmin(annotation);
    }

    /**
     * {@code @within}을 통해 클래스에 붙은 {@link CheckAdmin} 어노테이션을 받아
     * {@link #checkAdmin(CheckAdmin)} 메서드에 전달합니다.
     *
     * @implNote {@link Before} 어노테이션으로 메서드 실행 전에 로직을 추가할 수 있습니다.
     * <p>
     * {@code value}값의 {@code @within} 은 클래스에 붙은 어노테이션을 찾아내는데 사용됩니다.
     */
    @Before("@within(annotation)")
    public void onWithin(CheckAdmin annotation) {
        checkAdmin(annotation);
    }

    /**
     * {@link CheckAdmin} 어노테이션을 처리하는 메서드입니다.
     * <p>
     * 관리자가 아닌 경우 {@link NotAdminException} 예외를 발생시킵니다.
     */
    private void checkAdmin(CheckAdmin annotation) throws NotAdminException {
        if (!memberService.isAdmin()) {
            throw new NotAdminException();
        }
    }

}
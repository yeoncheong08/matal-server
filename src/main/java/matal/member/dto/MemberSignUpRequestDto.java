package matal.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import matal.member.entity.Member;
import matal.member.entity.Role;

@Builder
@Getter
@Setter
public class MemberSignUpRequestDto {
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String pwd;

    private String checkpwd;

    private String birth;

    private Role role;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .name(name)
                .pwd(pwd)
                .birth(birth)
                .role(Role.USER)
                .build();
    }
}

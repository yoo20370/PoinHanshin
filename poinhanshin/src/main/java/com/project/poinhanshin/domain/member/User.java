package com.project.poinhanshin.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class User {

    private Long id;

    @NotEmpty(message = "필수 값입니다.")
    private String loginId;

    @NotEmpty(message = "필수 값입니다.")
    private String password;

    @NotEmpty(message = "필수 값입니다.")
    private String name;

    @NotEmpty(message = "필수 값입니다.")
    private String birth;
}

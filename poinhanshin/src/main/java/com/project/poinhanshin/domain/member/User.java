package com.project.poinhanshin.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class User {

    private Long userno;

    @NotEmpty(message = "필수값입니다")
    private String id;

    @NotEmpty(message = "필수값입니다")
    private String password;

    @NotEmpty(message = "필수값입니다")
    private String name;

    private int age;

    private String phnum;

    @NotEmpty(message = "필수값입니다")
    private String mail;

    private String address;
}


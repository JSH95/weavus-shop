package com.weavus.weavusshoppingmall.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginDto {

    private final String id;
    private final String password;

    @Builder
    public LoginDto(String id, String password) {
        this.id = id;
        this.password = password;
    }
}

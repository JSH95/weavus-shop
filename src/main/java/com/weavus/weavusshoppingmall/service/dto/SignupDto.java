package com.weavus.weavusshoppingmall.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignupDto {

    private final String id;

    private final String password;

    private final String userEmail;

    private final String cardNumber;

    private final String cardCvc;

    private final String userName;

    private final String userEnName;

    @Builder
    public SignupDto(String id, String password, String userEmail, String cardNumber, String cardCvc, String userName, String userEnName) {
        this.id = id;
        this.password = password;
        this.userEmail = userEmail;
        this.cardNumber = cardNumber;
        this.cardCvc = cardCvc;
        this.userName = userName;
        this.userEnName = userEnName;
    }
}
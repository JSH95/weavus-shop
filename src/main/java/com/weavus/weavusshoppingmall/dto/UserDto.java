package com.weavus.weavusshoppingmall.dto;

import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String password;
    private String userEmail;
    private String cardNumber;
    private String cardCvc;
    private String userName;
    private String userEnName;
    private String isActive;
}
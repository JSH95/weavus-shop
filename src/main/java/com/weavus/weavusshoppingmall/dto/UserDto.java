package com.weavus.weavusshoppingmall.dto;

import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String password;
    private String userEmail;
    private int cardNumber;
    private int cardCvc;
    private String userName;
    private String userEnName;
    private String isActive;
}
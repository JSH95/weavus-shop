package com.weavus.weavusshoppingmall.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private String id;
    private String password;
    private String userEmail;
    private int cardNumber;
    private int cardCvc;
    private String userName;
    private String userEnName;
    //private LocalDate createdDate;
    private String isActive;
}
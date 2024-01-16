package com.weavus.weavusshoppingmall.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data

public class User {
    private String id;
    private String password;
    private String userEmail;
    private Integer cardNumber;
    private Integer cardCvc;
    private String userName;
    private String userEnName;
    private LocalDate createdDate;
}

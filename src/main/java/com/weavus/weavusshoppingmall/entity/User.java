package com.weavus.weavusshoppingmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
    public class User {
        private String id;
        private String password;
        private String userEmail;
        private String cardNumber;
        private String cardCvc;
        private String userName;
        private String userEnName;
        private LocalDate createdDate;
        private String isActive;
}
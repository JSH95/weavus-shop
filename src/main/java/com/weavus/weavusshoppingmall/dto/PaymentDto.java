package com.weavus.weavusshoppingmall.dto;

import lombok.Data;

@Data
public class PaymentDto {
    private String id;
    private String cardNumber;
    private String cardCvc;
    private String userEnName;
}
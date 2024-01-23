package com.weavus.weavusshoppingmall.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private String cartId;
    private String id;
    private String itemId;
    private String cartStock;
    private LocalDate createdAt;
    private List<Item> itemList = new ArrayList<>();

}
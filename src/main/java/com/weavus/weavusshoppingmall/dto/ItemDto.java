package com.weavus.weavusshoppingmall.dto;

import lombok.Data;

@Data
public class ItemDto {
    private String itemName;
    private String itemCategory;
    private String itemInfo;
    private String itemPrice;
    private String filepath;
    private String filename;
}

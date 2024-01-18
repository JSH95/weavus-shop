package com.weavus.weavusshoppingmall.dto;

import lombok.Data;

@Data
public class ItemDto {
    //private String itemId;
    private String itemName;
    private String itemCategory;
    private String itemInfo;
    private String itemPrice;
    //private String itemStatus;
    private String filepath;
    private String filename;
}

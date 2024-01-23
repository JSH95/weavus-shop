package com.weavus.weavusshoppingmall.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ItemDto {
    //private String itemId;
    private String itemName;
    private String itemCategory;
    private String itemInfo;
    private String itemPrice;
    private String itemStatus;
    private String filepath;
    private String filename;

    @Builder
    public ItemDto(String itemName, String itemCategory, String itemInfo, String itemPrice, String filepath, String filename) {
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemInfo = itemInfo;
        this.itemPrice = itemPrice;
        this.filepath = filepath;
        this.filename = filename;
    }
}

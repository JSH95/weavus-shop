package com.weavus.weavusshoppingmall.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class Item {

    @Id
    private String itemId;
    private String itemName;
    private String itemCategory;
    private String itemInfo;
    private String itemPrice;
    private String itemStatus;
    private String filepath;
    private String filename;

    @Builder
    public Item(String itemId, String itemName, String itemCategory, String itemInfo, String itemPrice, String itemStatus, String filepath, String filename) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemInfo = itemInfo;
        this.itemPrice = itemPrice;
        this.itemStatus = itemStatus;
        this.filepath = filepath;
        this.filename = filename;
    }
}

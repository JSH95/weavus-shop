package com.weavus.weavusshoppingmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {

private  String itemId;
    private String itemName;
    private String itemCategory;
    private String itemInfo;
    private String itemPrice;
    private String itemStatus;
    private String filepath;
    private String filename;

}

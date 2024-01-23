package com.weavus.weavusshoppingmall.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    @Id
    private String cartId;

    private String id;

    private String itemId;

    private String cartStock;

    private LocalDate createdAt;

//    @OneToMany
//    @JoinColumn(name = "itemId")
    private List<Item> itemList = new ArrayList<>();
//    private Item item; // carts.get(0), get(1) ...

    @Builder
    public Cart(String cartId, String id, String itemId, String cartStock) {
        this.cartId = cartId;
        this.id = id;
        this.itemId = itemId;
        this.cartStock = cartStock;
    }
}

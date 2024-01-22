package com.weavus.weavusshoppingmall.repo;

import com.weavus.weavusshoppingmall.entity.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {
    Item findById(String itemId);

    void saveItem(Item item);

    List<Item> getAllItems();

    List<Item> findByItemCategory(String num);

    void updateItem(Item item);

}

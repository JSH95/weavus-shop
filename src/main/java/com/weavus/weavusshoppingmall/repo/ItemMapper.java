package com.weavus.weavusshoppingmall.repo;

import com.weavus.weavusshoppingmall.entity.Item;
import com.weavus.weavusshoppingmall.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {
    Item findById(String itemId);

    void saveItem(Item item);

    List<Item> getAllItems();
}

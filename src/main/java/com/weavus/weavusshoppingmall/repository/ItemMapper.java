package com.weavus.weavusshoppingmall.repository;

import com.weavus.weavusshoppingmall.entity.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {

    void addItem(Item item);

    List<Item> getAllItems();
}

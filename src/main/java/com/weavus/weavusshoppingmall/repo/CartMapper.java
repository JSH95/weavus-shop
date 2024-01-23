package com.weavus.weavusshoppingmall.repo;

import com.weavus.weavusshoppingmall.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {

    void addItemToCart(@Param("item_id") String item_id, @Param("id") String id);

    List<Cart> findCartInfoByUserId(String id);

    void clearCartByUserId(String id);

}
package com.weavus.weavusshoppingmall.repository;

import com.weavus.weavusshoppingmall.entity.Cart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CartMapper {

    void addItemToCart(@Param("item_id") String item_id, @Param("id") String id);

    List<Cart> findCartInfoByUserId(String id);

    void clearCartByUserId(String id);
//    Cart findCartInfoByUserId(String id);


}

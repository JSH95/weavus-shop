package com.weavus.weavusshoppingmall.repository;

import com.weavus.weavusshoppingmall.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

    void save(User user);

    User findById(String id);

    User loginCheck(String id, String password);
}

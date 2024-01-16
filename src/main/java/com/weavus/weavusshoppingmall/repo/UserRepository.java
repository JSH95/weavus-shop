package com.weavus.weavusshoppingmall.repo;

import com.weavus.weavusshoppingmall.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;

@Mapper
public interface UserRepository{
User findById(String id);
User findByIdAndPassword(String id, String password);

}

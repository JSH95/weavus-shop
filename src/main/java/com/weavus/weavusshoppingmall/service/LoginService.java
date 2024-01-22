package com.weavus.weavusshoppingmall.service;

import com.weavus.weavusshoppingmall.dto.UserDto;
import com.weavus.weavusshoppingmall.entity.User;
import com.weavus.weavusshoppingmall.repo.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserMapper userMapper;

    public boolean signup(UserDto userDto) {

        User user = new User(
                userDto.getId(),
                userDto.getPassword(),
                userDto.getUserEmail(),
                userDto.getCardNumber(),
                userDto.getCardCvc(),
                userDto.getUserName(),
                userDto.getUserEnName(),
                null,
                userDto.getIsActive()
                );

        try {
            userMapper.saveUser(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
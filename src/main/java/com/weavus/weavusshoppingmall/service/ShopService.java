package com.weavus.weavusshoppingmall.service;

import com.weavus.weavusshoppingmall.entity.Cart;
import com.weavus.weavusshoppingmall.entity.Item;
import com.weavus.weavusshoppingmall.entity.User;
import com.weavus.weavusshoppingmall.repository.CartMapper;
import com.weavus.weavusshoppingmall.repository.ItemMapper;
import com.weavus.weavusshoppingmall.repository.UserMapper;
import com.weavus.weavusshoppingmall.service.dto.ItemDto;
import com.weavus.weavusshoppingmall.service.dto.LoginDto;
import com.weavus.weavusshoppingmall.service.dto.SignupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopService {

    private final UserMapper userMapper;
    private final ItemMapper itemMapper;
    private final CartMapper cartMapper;

    public void userSignup(SignupDto signupDto) {
        User user = User.builder()
                .id(signupDto.getId())
                .password(signupDto.getPassword())
                .userEmail(signupDto.getUserEmail())
                .cardNumber(signupDto.getCardNumber())
                .cardCvc(signupDto.getCardCvc())
                .userName(signupDto.getUserName())
                .userEnName(signupDto.getUserEnName())
                .build();
        userMapper.save(user);
    }

    public User userLogin(LoginDto loginDto) {
        return userMapper.loginCheck(loginDto.getId(), loginDto.getPassword());
    }

    public List<Item> getAllItems() {
        return itemMapper.getAllItems();
    }

    public void addItem(ItemDto itemDto, MultipartFile file) throws Exception {
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";

        UUID uuid = UUID.randomUUID();

        String fileName =uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);

        Item item = Item.builder()
                .itemName(itemDto.getItemName())
                .itemCategory(itemDto.getItemCategory())
                .itemInfo(itemDto.getItemInfo())
                .itemPrice(itemDto.getItemPrice())
                .itemStatus(itemDto.getItemStatus())
                .filepath("/files/" + fileName)
                .filename(fileName)
                .build();

        itemMapper.addItem(item);
    }

    public void addItemToCart(String item_id, String user_id) {

        cartMapper.addItemToCart(item_id, user_id);
    }

    public List<Cart> findCartInfoByUserId(String id) {
        return cartMapper.findCartInfoByUserId(id);
    }

    public User findUserById(String id) {
        return userMapper.findById(id);
    }

    public void clearCartByUserId(String id) {
        cartMapper.clearCartByUserId(id);
    }

}

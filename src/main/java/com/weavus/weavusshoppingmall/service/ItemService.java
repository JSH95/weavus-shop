package com.weavus.weavusshoppingmall.service;

import com.weavus.weavusshoppingmall.entity.Cart;
import com.weavus.weavusshoppingmall.entity.Item;
import com.weavus.weavusshoppingmall.repo.CartMapper;
import com.weavus.weavusshoppingmall.repo.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemMapper itemMapper;
    private final CartMapper cartMapper;

    public boolean write(Item item, MultipartFile file) throws Exception {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        item.setFilename(fileName);
        item.setFilepath("/files/" + fileName);

        try {
            itemMapper.saveItem(item);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public List<Item> findByItemStatus(String status){
        return itemMapper.findByItemStatus(status);
    }

    public List<Item> findByItemCategoryAndItemStatus(String num, String status){
        return itemMapper.findByItemCategoryAndItemStatus(num, status);
    }

    public boolean modify(Item item) {

        try {
            itemMapper.updateItem(item);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public void addItemToCart(String item_id, String user_id) {

        cartMapper.addItemToCart(item_id, user_id);
    }

    public List<Cart> findCartInfoByUserId(String id) {
        return cartMapper.findCartInfoByUserId(id);
    }

    public void clearCartByUserId(String id) {
        cartMapper.clearCartByUserId(id);
    }

    public List<Item> getAllItems(){
        return itemMapper.getAllItems();
    }

    public List<Item> findByItemCategory(String num){
        return itemMapper.findByItemCategory(num);
    }
}

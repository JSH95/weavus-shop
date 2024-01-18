package com.weavus.weavusshoppingmall.service;

import com.weavus.weavusshoppingmall.entity.Item;
import com.weavus.weavusshoppingmall.repo.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemMapper itemMapper;

    public boolean write(Item item, MultipartFile file) throws Exception {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        item.setFilename(fileName);
        item.setFilepath("/files/" + fileName);

        try{
            itemMapper.saveItem(item);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}

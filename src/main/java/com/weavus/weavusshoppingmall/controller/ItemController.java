package com.weavus.weavusshoppingmall.controller;

import com.weavus.weavusshoppingmall.dto.ItemDto;
import com.weavus.weavusshoppingmall.entity.Item;
import com.weavus.weavusshoppingmall.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/test") //어떤 url로 접근할 것인지 정해주는 어노테이션 //localhost:8080/board/write
    public String WriteForm() {
        return "test";
    }

    //여기에도 MultipartFile file 받아줌 //예외처리
    @PostMapping("/test")
    public String boardWritePro(Item item ,Model model, MultipartFile file) throws Exception {

       boolean result = itemService.write(item, file);
        if (result){
            model.addAttribute("msg","상품등록완료");
            return "redirect:/";
        }else {
            model.addAttribute("msg", "한번 더 확인해 주세요.");
            return "test";
        }
    }

}

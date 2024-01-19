package com.weavus.weavusshoppingmall.controller;

import com.weavus.weavusshoppingmall.dto.UserDto;
import com.weavus.weavusshoppingmall.entity.Item;
import com.weavus.weavusshoppingmall.entity.User;
import com.weavus.weavusshoppingmall.repo.ItemMapper;
import com.weavus.weavusshoppingmall.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @GetMapping("/")
    public String mainP(Model model, RedirectAttributes redirectAttributes){
        List<Item> items = itemService.getAllItems();
        model.addAttribute("items", items);
        return "index";
    }

    @GetMapping("detail/{id}")
    public String detail(@PathVariable String id, Model model, RedirectAttributes redirectAttributes){
        Item item = itemMapper.findById(id);
        model.addAttribute("detail", item);
        return "detail";
    }

    @GetMapping("itemSave/{id}")
    public String WriteForm(@PathVariable String id) {

        return "itemSave";
    }


    @PostMapping("itemSave/{id}")
    public String boardWritePro(@PathVariable String id, Item item , Model model, MultipartFile file) throws Exception {

       boolean result = itemService.write(item, file);
        if (result) {
            model.addAttribute("msg","상품등록완료");
            return "redirect:/";
        }else {
            model.addAttribute("msg", "한번 더 확인해 주세요.");
            return "itemSave";
        }
    }

}

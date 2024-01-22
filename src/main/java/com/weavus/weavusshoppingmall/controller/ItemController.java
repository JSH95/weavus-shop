package com.weavus.weavusshoppingmall.controller;

import com.weavus.weavusshoppingmall.entity.Item;
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

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

@GetMapping("/{num}") //카테고리 정리
    public String mainP(@PathVariable String num, Model model){

        List<Item> items = itemService.findByItemCategory(num);
        model.addAttribute("items", items);
        return "index";
    }

    @GetMapping("/") //기본 메인화면
    public String mainP(Model model){

        List<Item> items = itemService.getAllItems();
        model.addAttribute("items", items);
        return "index";
    }

    @GetMapping("detail/{id}")
    public String detail(@PathVariable String id, Model model){
        Item item = itemMapper.findById(id);
        model.addAttribute("detail", item);
        return "detail";
    }

    @GetMapping("itemSave")
    public String WriteForm() {

        return "itemSave";
    }

    @PostMapping("itemSave")
    public String boardWritePro(Item item , Model model, MultipartFile file) throws Exception {

        log.info("item={}", item);
       boolean result = itemService.write(item, file);
        if(result) {
            model.addAttribute("msg","상품등록완료");
            return "redirect:/";
        } else {
            model.addAttribute("msg", "한번 더 확인해 주세요.");
            return "itemSave";
        }
    }

    @GetMapping("itemModify/{id}")
    public String modify(@PathVariable String id, Model model){
        Item item = itemMapper.findById(id);
        model.addAttribute("modify", item);
        return "itemModify";
    }

    @PostMapping("itemModify")
    public String modifyPro(Item item , Model model) {

        boolean result = itemService.modify(item);

        if(result) {
            model.addAttribute("msg", "상품수정완료");
            return "redirect:/detail/" + item.getItemId();
        } else {
            model.addAttribute("msg", "한번 더 확인해 주세요.");
            return "itemModify";
        }
    }

}

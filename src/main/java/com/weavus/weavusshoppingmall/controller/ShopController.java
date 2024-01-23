package com.weavus.weavusshoppingmall.controller;

import com.weavus.weavusshoppingmall.dto.PaymentDto;
import com.weavus.weavusshoppingmall.entity.Cart;
import com.weavus.weavusshoppingmall.entity.User;
import com.weavus.weavusshoppingmall.repo.UserMapper;
import com.weavus.weavusshoppingmall.service.ItemService;
import com.weavus.weavusshoppingmall.service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ItemService itemService;
    private final LoginService loginService;
    private final UserMapper userMapper;


    @PostMapping("add-cart")
    public String addItemToCart(@RequestParam String item_id, @RequestParam String id, Model model) {

        itemService.addItemToCart(item_id, id);
        model.addAttribute("message", "상품을 카트에 추가했습니다.");
        model.addAttribute("searchUrl", "/");

        return "message";
    }

    @GetMapping("/{id}/cart")
    public String cartInfo(@PathVariable String id, Model model) {
        List<Cart> carts = itemService.findCartInfoByUserId(id);

        Object status = model.getAttribute("status");


        if(carts.isEmpty()) {
            model.addAttribute("message", "카트가 비어있습니다.");
            model.addAttribute("searchUrl", "/");

            return "message";
        }
        User user = userMapper.findById(id);

        model.addAttribute("carts", carts);
        model.addAttribute("user", user);
        if(status != null){
            model.addAttribute("status", true);
        }
        return "cart";
    }

    @PostMapping("/{id}/cart")
    public String payment(@ModelAttribute PaymentDto paymentDto,
                          HttpSession session, Model model, RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("user");
        boolean isValid = user.getCardNumber().equals(paymentDto.getCardNumber()) &&
                user.getCardCvc().equals(paymentDto.getCardCvc()) &&
                user.getUserEnName().equals(paymentDto.getUserEnName());

        if (isValid) {
            log.info("결제 성공");
            model.addAttribute("message", "결제가 완료되었습니다.");
            model.addAttribute("searchUrl", "/");
            itemService.clearCartByUserId(paymentDto.getId());
            return "message";
        } else {
            log.info("결제 실패");
//            redirectAttributes.addAttribute("message", "결제에 실패했습니다. 다시 시도해주세요.");
            redirectAttributes.addFlashAttribute("status", true);

            return "redirect:/" + paymentDto.getId() + "/cart";
        }
    }


    @GetMapping("/{id}/detail")
    public String detailCartInfo(@PathVariable String id) {

        return null;
    }

    @PostMapping("/{id}/detail")
    public String detailPayment(@ModelAttribute PaymentDto paymentDto,
                          HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");
        boolean isValid = user.getCardNumber().equals(paymentDto.getCardNumber()) &&
                user.getCardCvc().equals(paymentDto.getCardCvc()) &&
                user.getUserEnName().equals(paymentDto.getUserEnName());

        if (isValid) {
            log.info("결제 성공");
            model.addAttribute("message", "결제가 완료되었습니다.");
            model.addAttribute("searchUrl", "/");
            itemService.clearCartByUserId(paymentDto.getId());
            return "message";
        } else {
            log.info("결제 실패");
            model.addAttribute("message", "결제에 실패했습니다. 다시 시도해주세요.");
            model.addAttribute("searchUrl", "redirect:/detail");
            return "message";
        }
    }
}
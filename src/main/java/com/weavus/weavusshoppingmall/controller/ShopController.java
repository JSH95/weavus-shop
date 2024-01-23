package com.weavus.weavusshoppingmall.controller;

import com.weavus.weavusshoppingmall.entity.Cart;
import com.weavus.weavusshoppingmall.entity.Item;
import com.weavus.weavusshoppingmall.entity.User;
import com.weavus.weavusshoppingmall.service.ShopService;
import com.weavus.weavusshoppingmall.service.dto.ItemDto;
import com.weavus.weavusshoppingmall.service.dto.LoginDto;
import com.weavus.weavusshoppingmall.service.dto.SignupDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping("")
    public String home(Model model, HttpSession session) {
        List<Item> items = shopService.getAllItems();
        model.addAttribute("items", items);

        Object userObj = session.getAttribute("user");
        User user = (User) userObj;
        if(user != null) {
            List<Cart> cartInfo = shopService.findCartInfoByUserId(user.getId());
//            Cart cartInfo = shopService.findCartInfoByUserId(user.getId());
            model.addAttribute("cartInfo", cartInfo);
        }

        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginCheck(@ModelAttribute LoginDto loginDto, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        User user = shopService.userLogin(loginDto);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            redirectAttributes.addAttribute("status", true);
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute SignupDto signupDto, Model model) {
        try {
            shopService.userSignup(signupDto);
            model.addAttribute("message", "회원가입이 완료되었습니다.");
            model.addAttribute("searchUrl", "/");
        } catch (Exception e) {
            model.addAttribute("message", "이미 사용중인 아이디입니다.");
            model.addAttribute("searchUrl", "/signup");
        }
        return "message";
    }

    @GetMapping("/add-item")
    public String addItem() {
        return "itemForm";
    }

    @PostMapping("/add-item")
    public String addItemPost(@ModelAttribute ItemDto itemDto, MultipartFile file, Model model, RedirectAttributes redirectAttributes) throws Exception {
        try {
            shopService.addItem(itemDto, file);
            model.addAttribute("message", "상품등록이 완료되었습니다.");
            model.addAttribute("searchUrl", "/");

        } catch (Exception e) {
            model.addAttribute("message", "예기치못한 에러가 발생했습니다. 다시 시도해주세요.");
            model.addAttribute("searchUrl", "/add-item");

        }
        return "message";
    }


    @PostMapping("add-cart")
    public String addItemToCart(@RequestParam String item_id, @RequestParam String id, Model model) {

        shopService.addItemToCart(item_id, id);
        model.addAttribute("message", "상품을 카트에 추가했습니다.");
        model.addAttribute("searchUrl", "/");

        return "message";
    }

    @GetMapping("/{id}/cart")
    public String cartInfo(@PathVariable String id, Model model) {
        List<Cart> carts = shopService.findCartInfoByUserId(id);

        if(carts.isEmpty()) {
            model.addAttribute("message", "카트가 비어있습니다.");
            model.addAttribute("searchUrl", "/");

            return "message";
        }
        User user = shopService.findUserById(id);

        model.addAttribute("carts", carts);
        model.addAttribute("user", user);
        return "cart";
    }

    @PostMapping("/{id}/cart")
    public String payment(@PathVariable String id,
                          @RequestParam("cardNumber") String cardNumber,
                          @RequestParam("cardCvc") String cardCvc,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("user");
        boolean isValid = user.getCardNumber().equals(cardNumber) && user.getCardCvc().equals(cardCvc);

        if (isValid) {
            log.info("결제 성공");
            redirectAttributes.addAttribute("message", "결제가 완료되었습니다.");
            shopService.clearCartByUserId(id);
            return "redirect:/";

        } else {
            log.info("결제 실패");
            redirectAttributes.addAttribute("message", "결제에 실패했습니다. 다시 시도해주세요.");
            redirectAttributes.addAttribute("status", true);
            return "redirect:/" + id + "/cart";
        }
    }
}

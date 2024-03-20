package com.baejimeel.cakemall.controller;

import com.baejimeel.cakemall.config.auth.PrincipalDetails;
import com.baejimeel.cakemall.domain.item.Item;
import com.baejimeel.cakemall.domain.user.User;
import com.baejimeel.cakemall.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/")
    public String mainPageNoneLogin(Model model) {
        // 로그인을 안 한 경우
        List<Item> items = itemService.allItemView();
        model.addAttribute("items", items);

        return "main";
    }

    // 상품 등록 페이지 (GET)
    @GetMapping("/item/new")
    public String itemSaveForm() {

        return "/seller/itemForm";
    }

    // 상품 등록 (POST)
    @PostMapping("/item/new/pro")
    public String itemSave(Item item, @AuthenticationPrincipal PrincipalDetails principalDetails, MultipartFile imgFile) throws Exception {
        if(principalDetails.getUser().getRole().equals("ROLE_SELLER")) {
            // 판매자
            item.setSeller(principalDetails.getUser());
            itemService.saveItem(item, imgFile);

            return "redirect:/main";
        } else {
            // 일반 회원이면 거절 -> main
            return "redirect:/main";
        }
    }

    // 상품 수정 페이지 (GET)
    @GetMapping("/item/modify/{id}")
    public String itemModifyForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("item", itemService.itemView(id));

        return "/seller/itemModify";
    }

    // 상품 수정 (POST)
    @PostMapping("/item/modify/pro/{id}")
    public String itemModify(Item item, @PathVariable("id") Integer id, @AuthenticationPrincipal PrincipalDetails principalDetails, MultipartFile imgFile) throws Exception{

        if(principalDetails.getUser().getRole().equals("ROLE_SELLER")) {
            // 판매자
            User user = itemService.itemView(id).getSeller();

            if(user.getId() == principalDetails.getUser().getId()) {
                // 상품을 올린 판매자 id와 현재 로그인 중인 판매자의 id가 같아야 수정 가능
                itemService.itemModify(item, id, imgFile);

                return "redirect:/main";
            } else {
                return "redirect:/main";
            }
        } else {
            // 일반 회원이면 거절 -> main
            return "redirect:/main";
        }
    }

    // 상품 상세 페이지
    @GetMapping("/item/view/{id}")
    public String itemView(Model model, @PathVariable("id") Integer id) {

        model.addAttribute("item", itemService.itemView(id));

        return "/seller/itemView";
    }


    // 상품 삭제
//    @GetMapping("/item/delete/{id}")
//    public String itemDelete(@PathVariable("id") Integer id) {
//
//        itemService.itemDelete(id);
//
//        return "/main";
//    }
}

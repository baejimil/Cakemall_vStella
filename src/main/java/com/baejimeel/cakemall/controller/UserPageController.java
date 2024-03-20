package com.baejimeel.cakemall.controller;

import com.baejimeel.cakemall.config.auth.PrincipalDetails;
import com.baejimeel.cakemall.domain.cart.Cart;
import com.baejimeel.cakemall.domain.cartitem.CartItem;
import com.baejimeel.cakemall.domain.item.Item;
import com.baejimeel.cakemall.domain.user.User;
import com.baejimeel.cakemall.service.CartService;
import com.baejimeel.cakemall.service.ItemService;
import com.baejimeel.cakemall.service.UserPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


// 구매자에 해당하는 페이지 관리
// 마이페이지, 장바구니, 회원정보수정, 주문, 주문취소
@RequiredArgsConstructor
@Controller
public class UserPageController {

    private final UserPageService userPageService;
    private final CartService cartService;
    private final ItemService itemService;



    // 유저 페이지 접속
    @GetMapping("/user/{id}")
    public String userPage(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){

        // 로그인이 되어있는 유저의 id와 유저 페이지에 접속하는 id가 같아야 함
        if (principalDetails.getUser().getId() == id){
            model.addAttribute("user", userPageService.findUser(id));

            return "user/userPage";
        }else {
            return "redirect:/main";
        }
    }

    // 회원 정보 수정
    @GetMapping("/user/modify/{id}")
    public String userModify(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
        // 로그인이 되어있는 유저의 id와 수정페이지에 접속하는 id가 같아야 함
        if (principalDetails.getUser().getId() == id){

            model.addAttribute("user", userPageService.findUser(id));

            return "userModify";
        }else {
            return "redirect:/main";
        }
    }

    // 수정 실행
    @PostMapping("/user/update/{id}")
    public String userUpdate(@PathVariable("id") Integer id, User user){
        userPageService.userModify(user);

        return "redirect:/user/{id}";
    }

    // 장바구니 페이지 접속
    @GetMapping("/user/cart/{id}")
    public String userCartPage(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
        // 로그인이 되어있는 유저의 id와 장바구니에 접속하는 id가 같아야 함
        if (principalDetails.getUser().getId() == id){
            User user = userPageService.findUser(id);
            // 로그인 되어 있는 유저에 해당하는 장바구니 가져오기
            Cart userCart = user.getCart();

            // 장바구니에 들어있는 아이템 모두 가져오기
            List<CartItem> cartItemList = cartService.allUserCartView(userCart);

            model.addAttribute("totalCount", userCart.getCount());
            model.addAttribute("cartItems", cartItemList);
            model.addAttribute("user", userPageService.findUser(id));

            return "user/userCart";
        }else { // 로그인 id와 장바구니 접속 id가 다른 경우
            return "redirect:/main";
        }
    }

    // 장바구니에 물건 넣기
    @PostMapping("/user/cart/{id}/{itemId}")
    public String addCartItem(@PathVariable("id") Integer id, @PathVariable("itemId") Integer itemId, int amount){
        User user = userPageService.findUser(id);
        Item item = itemService.itemView(itemId);

        cartService.addCart(user, item, amount);

        return "redirect:/item/view/{itemId}";
    }

    // 장바구니에서 물건 삭제
    // 삭제하고 남은 상품의 총 개수
    @GetMapping("/user/cart/{id}/{cartItemId}/delete")
    public String deleteCartItem(@PathVariable("id") Integer id, @PathVariable("cartItemId") Integer itemId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
        // 로그인 유저 id와 장바구니 유저의 id가 같아야 함
        if (principalDetails.getUser().getId() == id){
            //itemId로 장바구니 상품 찾기
            CartItem cartItem = cartService.findCartItemById(itemId);

            // 해당 유저의 카트 찾기
            Cart userCart = cartService.findUserCart(id);

            // 장바구니 전체 수량 감소
            userCart.setCount(userCart.getCount()-cartItem.getCount());

            // 장바구니 물건 삭제
            cartService.cartItemDelete(itemId);

            // 해당 유저의 장바구니 상품들
            List<CartItem> cartItemList = cartService.allUserCartView(userCart);

            model.addAttribute("totalCount", userCart.getCount());
            model.addAttribute("cartItems", cartItemList);
            model.addAttribute("user", userPageService.findUser(id));

            return "user/userCart";
        }else { // 로그인 id와 장바구니 삭제하려는 유저의 id가 같지 않는 경우
            return "redirect:/main";
        }
    }


}

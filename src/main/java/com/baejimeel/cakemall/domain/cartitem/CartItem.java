package com.baejimeel.cakemall.domain.cartitem;

import com.baejimeel.cakemall.domain.cart.Cart;
import com.baejimeel.cakemall.domain.item.Item;
import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="item_id")
    private Item item;

    private int cartCount; // 카트에 담긴 상품 개수

}

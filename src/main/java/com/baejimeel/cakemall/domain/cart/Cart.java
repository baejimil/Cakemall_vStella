package com.baejimeel.cakemall.domain.cart;

import com.baejimeel.cakemall.domain.cartitem.CartItem;
import com.baejimeel.cakemall.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    User user; // 해당 유저의 장바구니

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cart_items = new ArrayList<>();
}

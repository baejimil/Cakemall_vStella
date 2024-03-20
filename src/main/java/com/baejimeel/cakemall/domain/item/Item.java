package com.baejimeel.cakemall.domain.item;

import com.baejimeel.cakemall.domain.cartitem.CartItem;
import com.baejimeel.cakemall.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name; // 상품 이름

    private String text; // 상품에 대한 상세설명

    private int price; // 상품 가격

    private int count; // 판매 개수

    private int stock; // 재고

    private boolean isSoldout; // 상품 상태 (판매중 / 품절)

    private String imgName;

    private String imgPath;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id")
    private User seller; // 판매자 아이디

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user; // 판매자 아이디

    @OneToMany(mappedBy = "item")
    private List<CartItem> cart_items = new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate; // 상품 등록 날짜

    @PrePersist // DB에 INSERT 되기 직전에 실행. 즉 DB에 값을 넣으면 자동으로 실행됨
    public void createDate() {
        this.createDate = LocalDate.now();
    }
}

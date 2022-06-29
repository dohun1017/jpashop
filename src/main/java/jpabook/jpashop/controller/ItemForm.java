package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public abstract class ItemForm {

    private Long id;

    @NotEmpty(message = "상품 이름은 필수 입니다.")
    private String name;
    private int price;
    private int stockQuantity;
}

package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class BookForm extends ItemForm{

    private String author;
    private String isbn;
}

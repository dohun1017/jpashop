package jpabook.jpashop.controller;


import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(@ModelAttribute("form") @Valid BookForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "items/createItemForm";
        }

        Book book = Book.createBook(form);

        itemService.saveItem(book);
        return "redirect:/items";
    }

    @GetMapping("/items")
    public String itemList(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);

        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book item = (Book) itemService.findOne(itemId);

        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") Long itemId,
                             @ModelAttribute("form") @Valid BookForm form,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "items/updateItemForm";
        }

        /**
         * 병합(merge)
         *
         * 준영속성 객체를 병합한다.
         * 이 때 모든게 변경되기 떄문에 null이 들어갈 수 있다.
         */
//        Book item = Book.createBook(form);
//        itemService.saveItem(item);

        /**
         * 변경감지
         *
         * 준영속성 객체를 영속 상태로 바꿔서 변경한다.
         */
        itemService.updateItem(form.getId(), form.getName(), form.getPrice(), form.getStockQuantity());

        return "redirect:/items";
    }
}

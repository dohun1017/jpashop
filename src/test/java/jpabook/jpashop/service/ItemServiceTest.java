package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Album;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 상품저장() throws Exception {
        //given
        Item item1 = new Book();
        item1.setName("상품1");

        Item item2 = new Album();
        item2.setId(2L);
        item2.setName("상품2");

        //when
        Long savedId1 = itemService.saveItem(item1);
        Long savedId2 = itemService.saveItem(item2);

        //then
        assertEquals(savedId1, item1.getId());
        assertEquals(savedId2, item2.getId());
    }

    @Test
    public void 상품_조회_단일() throws Exception {
        //given
        Item item = new Book();
        item.setName("상품-책");

        Long itemId = itemService.saveItem(item);

        //when
        Item findItem = itemService.findOne(itemId);

        //then
        assertEquals(findItem, item);
    }

    @Test
    public void 상품_조회_전체() throws Exception {
        //given
        Item item1 = new Book();
        item1.setName("상품-책1");
        Item item2 = new Book();
        item2.setName("상품-책2");

        itemService.saveItem(item1);
        itemService.saveItem(item2);

        //when
        List<Item> items = itemService.findItems();

        //then
        assertThat(items).contains(item1, item2);
    }
}
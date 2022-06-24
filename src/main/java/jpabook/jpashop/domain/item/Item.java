package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
/**
 * InheritanceType.JOINED: 가장 정규적인
 * InheritanceType.SINGLE_TABLE: 다 때려박기
 * InheritanceType.TABLE_PER_CLASS: 이번 예제의 경우 세개의 테이블만 있음(Book, Album, Movie)
 */
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    /**
     * 실무에서 사용하면 안됨.
     * 항목 추가에 문제가 있음.
     */
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}

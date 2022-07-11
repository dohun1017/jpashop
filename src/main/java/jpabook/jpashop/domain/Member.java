package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String name;

    /**
     * 보통은 둘다 표기해준다.
     * Address 클래스가 @Embeddable 어노테이션을 가지고 있어거나 사용하는 쪽에서 @Embedded 어노테이션을 사용해도 된다.
     */
    @Embedded
    private Address address;

    /**
     * ReadOnly
     */
    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}

package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "deliver_id")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    /**
     * EnumType.ORDINAL: 숫자로 들어감
     * EnumType.STRING: 문자로 들어감
     */
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY, COMP
}

package com.naneun.mall.domain.entity;

import com.naneun.mall.domain.link.OrderSheetToProduct;
import com.naneun.mall.domain.enumeration.DeliveryStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private int totalPrice;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "orderSheet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderSheetToProduct> orderSheetToProducts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Delivery delivery;

    @Builder
    private OrderSheet(Long id, int totalPrice, Member member, Delivery delivery) {
        this.id = id;
        this.deliveryStatus = DeliveryStatus.READY;
        this.totalPrice = totalPrice;
        this.orderSheetToProducts = new ArrayList<>();
        this.member = member;
        this.delivery = delivery;
    }

    public static OrderSheet of(int totalPrice, Member member, Delivery delivery,
                                OrderSheetToProduct... orderSheetToProducts) {

        OrderSheet orderSheet = OrderSheet.builder()
                .totalPrice(totalPrice)
                .member(member)
                .delivery(delivery)
                .build();

        for (OrderSheetToProduct orderSheetToProduct : orderSheetToProducts) {
            orderSheet.addOrderSheetToProducts(orderSheetToProduct);
        }

        return orderSheet;
    }

    public void addOrderSheetToProducts(OrderSheetToProduct orderSheetToProduct) {
        orderSheetToProducts.add(orderSheetToProduct);
    }
}

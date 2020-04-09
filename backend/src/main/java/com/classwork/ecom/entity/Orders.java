package com.classwork.ecom.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "delivery status of order")
    private boolean delivered;

    @ApiModelProperty(notes = "date order was placed")
    private LocalDateTime orderDate = LocalDateTime.now();

    @ApiModelProperty(notes = "owner of order")
    @OneToOne
    private User user;

    @ApiModelProperty(notes = "list of ordered items")
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Transient
    public Double getTotalAmount() {
        double sum = 0D;
        List<OrderItem> orderItems = getOrderItems();

        for (OrderItem oi : orderItems) {
            sum += oi.getAmount();
        }

        return sum;
    }

    public void addItem(OrderItem item) {
        orderItems.add(item);
        item.setOrders(this);
    }

    public void removeItem(OrderItem item) {
        orderItems.remove(item);
        item.setOrders(null);
    }
}

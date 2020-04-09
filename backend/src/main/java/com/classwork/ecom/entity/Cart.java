package com.classwork.ecom.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ApiModel(description = "Cart Entity attributes and methods")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "expiry status of cart")
    private boolean expired;

    @ApiModelProperty(notes = "owner of cart")
    @OneToOne
    private User user;

    @ApiModelProperty(notes = "list of items in the cart")
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    @Transient
    public Double getTotalAmount() {
        double sum = 0D;
        List<CartItem> cartItems = getCartItems();

        for (CartItem ci : cartItems) {
            sum += ci.getAmount();
        }

        return sum;
    }

    public void addItem(CartItem item) {
        cartItems.add(item);
        item.setCart(this);
    }

    public void removeItem(CartItem item) {
        cartItems.remove(item);
        item.setCart(null);
    }
}

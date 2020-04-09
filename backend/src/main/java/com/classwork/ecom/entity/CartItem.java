package com.classwork.ecom.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Getter
@Setter
@Entity
@ApiModel(description = "CartItem Entity attributes and methods")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "Quantity should be a minimum of one")
    @ApiModelProperty(notes = "Quantity of item in cart")
    private int quantity;

    @ApiModelProperty(notes = "Cart item belongs to")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    @ApiModelProperty(notes = "Product added to cart")
    @ManyToOne
    private Product product;

    @Transient
    public Double getAmount() {
        return getProduct().getPrice() * getQuantity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem)) return false;
        return id != null && id.equals(((CartItem) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}

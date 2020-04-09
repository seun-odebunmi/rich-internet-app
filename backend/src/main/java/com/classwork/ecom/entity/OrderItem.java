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
@ApiModel(description = "OrderItem Entity attributes and methods")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "Name of ordered product")
    private String name;

    @ApiModelProperty(notes = "Price of ordered product")
    private double price;

    @Min(value = 1, message = "Quantity should be a minimum of one")
    @ApiModelProperty(notes = "Quantity of ordered product")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Orders orders;

    @Transient
    public Double getAmount() {
        return getPrice() * getQuantity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        return id != null && id.equals(((OrderItem) o).getId());
    }

    @Override
    public int hashCode() {
        return 32;
    }
}

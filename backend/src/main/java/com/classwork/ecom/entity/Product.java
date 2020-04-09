package com.classwork.ecom.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@ApiModel(description = "Product information")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, message = "Name should be a minimum of two characters")
    @ApiModelProperty(notes = "Name of the product")
    @Column(unique = true)
    private String name;

    @ApiModelProperty(notes = "Price of the product")
    private Double price;

    @Min(value = 1, message = "Quantity should be a minimum of one")
    @ApiModelProperty(notes = "Quantity of the product")
    private int quantity;

    @ApiModelProperty(notes = "Description of the product")
    private String description;
}

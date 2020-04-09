package com.classwork.ecom.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "Review Message")
    private String message;

    @ApiModelProperty(notes = "User who left review")
    @ManyToOne
    private User user;

    @ApiModelProperty(notes = "Product that was reviewed")
    @ManyToOne
    private Product product;
}

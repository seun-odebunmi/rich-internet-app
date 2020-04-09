package com.classwork.ecom.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, message = "First name should be at least two characters")
    @ApiModelProperty(notes = "First name of the user")
    private String firstName;

    @Size(min = 2, message = "Last name should be at least two characters")
    @ApiModelProperty(notes = "Last name of the user")
    private String lastName;

    @Min(value = 13, message = "User should be 13 and above")
    @ApiModelProperty(notes = "Age of the user. Should be 13 and above")
    private int age;

    @NotEmpty(message = "Email address should not be empty")
    @ApiModelProperty(notes = "Email of the user")
    @Email
    @Column(unique = true)
    private String email;

    @ApiModelProperty(notes = "Phone number of the user")
    @Column(unique = true)
    private String phoneNumber;

    @ApiModelProperty(notes = "Address of the user")
    private String address;

    @ApiModelProperty(notes = "Gender of the user")
    private String gender;

    @ApiModelProperty(notes = "User role of the user")
    @OneToOne
    private UserType userType;
}

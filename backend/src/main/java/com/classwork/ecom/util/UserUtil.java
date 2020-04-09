package com.classwork.ecom.util;

import com.classwork.ecom.entity.User;
import com.classwork.ecom.entity.UserType;
import com.classwork.ecom.repository.UserTypeRepository;
import com.classwork.ecom.service.UserService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@Component
public class UserUtil {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTypeRepository userTypeRepository;

    public void generateUser(int index) {
        Faker faker = new Faker(new Random(index));
        Optional<UserType> userType = userTypeRepository.findById(faker.number().numberBetween(Long.valueOf(1), Long.valueOf(3)));
        User user = new User();
        user.setAddress(faker.address().fullAddress());
        user.setAge(faker.number().numberBetween(13, 69));
        user.setEmail(faker.internet().emailAddress());
        user.setFirstName(faker.name().firstName());
        user.setGender(faker.demographic().sex());
        user.setLastName(faker.name().lastName());
        user.setPhoneNumber(faker.phoneNumber().phoneNumber());
        user.setUserType(userType.get());

        userService.createUser(user);
    }

    public void generateSampleUsers(int number) {
        long count = userService.usersCount();

        if (count < 1) {
            for (int i = 0; i < number; i++) {
                generateUser(i);
            }
        } else if (count > 0 && count < number) {
            for (int i = 0; i < (number - count); i++) {
                generateUser(i);
            }
        }
    }
}

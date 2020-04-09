package com.classwork.ecom.util;

import com.classwork.ecom.entity.UserType;
import com.classwork.ecom.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserTypeUtil {

    @Autowired
    private UserTypeRepository userTypeRepository;

    public void generateUserType(String name) {
        UserType userType = new UserType();
        userType.setName(name);

        userTypeRepository.save(userType);
    }

    public void generateSampleUserTypes() {
        List<String> userRoles = new ArrayList<>();
        userRoles.add("User");
        userRoles.add("Admin");

        for (String userRole : userRoles) {
            Optional<UserType> userType = userTypeRepository.findByName(userRole);

            if (!userType.isPresent()) {
                generateUserType(userRole);
            }
        }
    }
}

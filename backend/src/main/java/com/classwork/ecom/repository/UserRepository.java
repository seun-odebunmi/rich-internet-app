package com.classwork.ecom.repository;

import com.classwork.ecom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

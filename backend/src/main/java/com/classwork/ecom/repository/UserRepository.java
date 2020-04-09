package com.classwork.ecom.repository;

import com.classwork.ecom.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByFirstNameContaining(String firstName, Pageable pageable);

    Page<User> findByLastNameContaining(String lastName, Pageable pageable);

    Page<User> findByFirstNameContainingAndLastNameContaining(String firstName, String lastName, Pageable pageable);
}

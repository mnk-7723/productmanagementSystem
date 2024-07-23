package com.example.productmanagement.system.persistence.dao.user;

import com.example.productmanagement.system.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}

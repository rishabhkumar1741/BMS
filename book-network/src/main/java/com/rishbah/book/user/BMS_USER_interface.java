package com.rishbah.book.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BMS_USER_interface extends JpaRepository<BMS_User,Integer> {
    Optional<BMS_User> findByEmail(String email);
}

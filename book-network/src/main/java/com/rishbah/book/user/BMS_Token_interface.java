package com.rishbah.book.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BMS_Token_interface extends JpaRepository<BMS_Token, Integer> {
    Optional<BMS_Token> findByToken(String token);
}

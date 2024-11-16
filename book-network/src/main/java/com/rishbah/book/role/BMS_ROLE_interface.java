package com.rishbah.book.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BMS_ROLE_interface extends JpaRepository<BMS_Role, Integer> {
    Optional<BMS_Role> findByName(String role);
}

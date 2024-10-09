package com.example.last.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.last.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

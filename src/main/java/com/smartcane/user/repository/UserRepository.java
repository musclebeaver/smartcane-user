package com.smartcane.user.repository;

import com.smartcane.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByIdIn(Collection<Long> ids);
}

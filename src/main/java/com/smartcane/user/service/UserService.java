package com.smartcane.user.service;

import com.smartcane.user.dto.*;
import com.smartcane.user.entity.User;
import com.smartcane.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository repo;

    public UserDto create(UserCreateRequest req) {
        repo.findByEmail(req.email()).ifPresent(u -> { throw new IllegalArgumentException("email duplicated"); });
        User u = User.builder()
                .email(req.email()).nickname(req.nickname()).birthDate(req.birthDate())
                .role("USER").status("ACTIVE").build();
        u = repo.save(u);
        return toDto(u);
    }

    @Transactional(readOnly = true)
    public UserDto get(Long id) {
        return toDto(repo.findById(id).orElseThrow(() -> new EntityNotFoundException("user not found")));
    }

    @Transactional(readOnly = true)
    public Optional<UserDto> getByEmail(String email) {
        return repo.findByEmail(email).map(this::toDto);
    }

    public UserDto update(Long id, UserUpdateRequest req) {
        User u = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("user not found"));
        if (req.nickname() != null && !req.nickname().isBlank()) u.setNickname(req.nickname());
        if (req.birthDate() != null) u.setBirthDate(req.birthDate());
        if (req.status() != null) u.setStatus(req.status());
        return toDto(u);
    }

    public void softDelete(Long id) {
        User u = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("user not found"));
        u.setStatus("DELETED");
    }

    @Transactional(readOnly = true)
    public List<UserDto> findByIds(Collection<Long> ids) {
        return repo.findByIdIn(ids).stream().map(this::toDto).toList();
    }

    private UserDto toDto(User u) {
        return new UserDto(u.getId(), u.getEmail(), u.getNickname(), u.getBirthDate(), u.getRole(), u.getStatus());
    }
}

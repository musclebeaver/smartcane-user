package com.smartcane.user.controller;

import com.smartcane.user.dto.*;
import com.smartcane.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService svc;

    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserCreateRequest req) {
        return ResponseEntity.status(201).body(svc.create(req));
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable Long id) { return svc.get(id); }

    @PatchMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserUpdateRequest req) {
        return svc.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        svc.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}

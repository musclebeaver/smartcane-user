package com.smartcane.user.controller;

import com.smartcane.user.dto.UserDto;
import com.smartcane.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/internal/users")
@RequiredArgsConstructor
public class InternalUserController {
    private final UserService svc;

    @GetMapping("/{id}")
    public UserDto internalGet(@PathVariable Long id) { return svc.get(id); }

    @GetMapping
    public List<UserDto> internalGetBulk(@RequestParam List<Long> ids) {
        return svc.findByIds(ids);
    }

    @GetMapping("/by-email")
    public UserDto getByEmail(@RequestParam String email) {
        return svc.getByEmail(email).orElseThrow(() -> new NoSuchElementException("user not found"));
    }
}

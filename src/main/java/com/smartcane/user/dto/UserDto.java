package com.smartcane.user.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
public record UserDto(Long id, String email, String nickname,
                      LocalDate birthDate, String role, String status) {}
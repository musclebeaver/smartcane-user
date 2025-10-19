package com.smartcane.user.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record UserCreateRequest(@Email @NotBlank String email,
                                @NotBlank String nickname,
                                LocalDate birthDate) {}
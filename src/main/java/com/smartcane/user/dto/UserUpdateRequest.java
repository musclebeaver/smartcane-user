package com.smartcane.user.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;


public record UserUpdateRequest(String nickname,
                                LocalDate birthDate,
                                String status) {}
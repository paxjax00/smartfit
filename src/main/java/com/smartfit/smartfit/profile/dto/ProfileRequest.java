package com.smartfit.smartfit.profile.dto;

import com.smartfit.smartfit.profile.enums.ActivityLevel;
import com.smartfit.smartfit.profile.enums.FitnessGoal;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@NotNull
public record ProfileRequest(

        @Past(message = "Date of birth must be in the past")
        LocalDate dateOfBirth,

        @DecimalMin(value = "50.0", message = "Height must be at least 50 cm")
        @DecimalMax(value = "300.0", message = "Height must not exceed 300 cm")
        BigDecimal heightCm,

        @DecimalMin(value = "20.0", message = "Weight must be at least 20 kg")
        @DecimalMax(value = "500.0", message = "Weight must not exceed 500 kg")
        BigDecimal currentWeightKg,

        FitnessGoal fitnessGoal,

        ActivityLevel activityLevel
) {
}
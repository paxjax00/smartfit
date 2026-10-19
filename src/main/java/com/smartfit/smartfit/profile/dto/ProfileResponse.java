package com.smartfit.smartfit.profile.dto;

import com.smartfit.smartfit.profile.enums.ActivityLevel;
import com.smartfit.smartfit.profile.enums.FitnessGoal;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProfileResponse(
        Long id,
        Long userId,
        LocalDate dateOfBirth,
        BigDecimal heightCm,
        BigDecimal currentWeightKg,
        FitnessGoal fitnessGoal,
        ActivityLevel activityLevel
) {
}
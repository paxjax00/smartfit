
package com.smartfit.smartfit.profile.controller;

import com.smartfit.smartfit.profile.dto.ProfileRequest;
import com.smartfit.smartfit.profile.dto.ProfileResponse;
import com.smartfit.smartfit.profile.service.UserProfileService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {

    private final UserProfileService profileService;

    public UserProfileController(UserProfileService profileService) {
        this.profileService = profileService;
    }

    @PutMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public ProfileResponse createOrUpdateMyProfile(
            Authentication authentication,
            @Valid @RequestBody ProfileRequest request
    ) {
        String email = authentication.getName();

        return profileService.createOrUpdateProfile(email, request);
    }

    @GetMapping("/me")
    public ProfileResponse getMyProfile(
            Authentication authentication
    ) {
        String email = authentication.getName();

        return profileService.getMyProfile(email);
    }
}

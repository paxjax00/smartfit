package com.smartfit.smartfit.profile.service;

import com.smartfit.smartfit.profile.dto.ProfileRequest;
import com.smartfit.smartfit.profile.dto.ProfileResponse;
import com.smartfit.smartfit.profile.entity.UserProfile;
import com.smartfit.smartfit.profile.repository.UserProfileRepository;
import com.smartfit.smartfit.user.entity.User;
import com.smartfit.smartfit.user.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserProfileService {

    private final UserProfileRepository profileRepository;
    private final UserRepository userRepository;

    public UserProfileService(
            UserProfileRepository profileRepository,
            UserRepository userRepository
    ) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ProfileResponse createOrUpdateProfile(
            String email,
            ProfileRequest request
    ) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                ));

        UserProfile profile = profileRepository
                .findByUserId(user.getId())
                .orElseGet(() -> {
                    UserProfile newProfile = new UserProfile();
                    newProfile.setUser(user);
                    return newProfile;
                });

        profile.setDateOfBirth(request.dateOfBirth());
        profile.setHeightCm(request.heightCm());
        profile.setCurrentWeightKg(request.currentWeightKg());
        profile.setFitnessGoal(request.fitnessGoal());
        profile.setActivityLevel(request.activityLevel());

        UserProfile savedProfile = profileRepository.save(profile);

        return toResponse(savedProfile);
    }

    @Transactional(readOnly = true)
    public ProfileResponse getMyProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                ));

        UserProfile profile = profileRepository
                .findByUserId(user.getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Profile not found"
                ));

        return toResponse(profile);
    }

    private ProfileResponse toResponse(UserProfile profile) {
        return new ProfileResponse(
                profile.getId(),
                profile.getUser().getId(),
                profile.getDateOfBirth(),
                profile.getHeightCm(),
                profile.getCurrentWeightKg(),
                profile.getFitnessGoal(),
                profile.getActivityLevel()
        );
    }
}
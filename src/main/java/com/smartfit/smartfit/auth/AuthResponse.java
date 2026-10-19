
package com.smartfit.smartfit.auth;

import com.smartfit.smartfit.user.dto.UserResponse;

public record AuthResponse(
        UserResponse user,
        String token
) {
}
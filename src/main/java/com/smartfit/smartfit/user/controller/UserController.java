package com.smartfit.smartfit.user.controller;
import com.smartfit.smartfit.user.dto.RegisterRequest;
import com.smartfit.smartfit.user.dto.UserResponse;
import com.smartfit.smartfit.user.entity.User;
import com.smartfit.smartfit.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@RestController @RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    } @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers() .stream()
                .map(user -> new UserResponse( user.getId(),
                        user.getFirstName(), user.getLastName(), user.getEmail() ))
                .toList(); }
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id)
                .orElseThrow(() ->
                        new ResponseStatusException( HttpStatus.NOT_FOUND, "User not found" ));
        return new UserResponse( user.getId(), user.getFirstName(),
                user.getLastName(), user.getEmail() ); }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(
            @Valid @RequestBody RegisterRequest request ) {
        User createdUser = userService.registerUser(request);
        UserResponse response = new UserResponse(
                createdUser.getId(), createdUser.getFirstName(),
                createdUser.getLastName(), createdUser.getEmail()
        );
        return ResponseEntity .status(HttpStatus.CREATED) .body(response); } }
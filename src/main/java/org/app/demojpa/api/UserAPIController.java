package org.app.demojpa.api;


import jakarta.validation.Valid;
import org.app.demojpa.request.UserRequest;
import org.app.demojpa.response.APIResponse;
import org.app.demojpa.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/users")
public class UserAPIController {
    private UserService userService;

    public UserAPIController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<APIResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
        APIResponse response = new APIResponse();
        response.setMessage("User created successfully");
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }
}

package mx.kinich49.itemtracker.controllers.main;

import mx.kinich49.itemtracker.JsonApi;
import mx.kinich49.itemtracker.exceptions.BusinessException;
import mx.kinich49.itemtracker.models.database.User;
import mx.kinich49.itemtracker.requests.main.AuthenticationResponse;
import mx.kinich49.itemtracker.requests.main.UserRequest;
import mx.kinich49.itemtracker.services.JWTService;
import mx.kinich49.itemtracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public UserController(UserService userService,
                          JWTService jwtService,
                          UserDetailsService userDetailsService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping
    public ResponseEntity<JsonApi<AuthenticationResponse>> createUser(@RequestBody UserRequest userRequest) {
        try {
            return userService.addUser(userRequest)
                    .map(User::getUsername)
                    .map(userDetailsService::loadUserByUsername)
                    .map(jwtService::generateToken)
                    .map(AuthenticationResponse::new)
                    .map(JsonApi::new)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> {
                        JsonApi<AuthenticationResponse> jsonApi = new JsonApi<>("Something went wrong");
                        return new ResponseEntity<>(jsonApi, HttpStatus.BAD_REQUEST);
                    });

        } catch (BusinessException e) {
            JsonApi<AuthenticationResponse> jsonApi = new JsonApi<>(e.getMessage());
            return new ResponseEntity<>(jsonApi, HttpStatus.BAD_REQUEST);
        }
    }
}

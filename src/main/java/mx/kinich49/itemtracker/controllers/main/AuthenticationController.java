package mx.kinich49.itemtracker.controllers.main;

import mx.kinich49.itemtracker.JsonApi;
import mx.kinich49.itemtracker.requests.main.AuthenticationRequest;
import mx.kinich49.itemtracker.requests.main.AuthenticationResponse;
import mx.kinich49.itemtracker.services.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JWTService jwtService,
                                    UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return Optional.ofNullable(userDetailsService.loadUserByUsername(request.getUsername()))
                .map(jwtService::generateToken)
                .map(AuthenticationResponse::new)
                .map(JsonApi::ok)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    JsonApi<AuthenticationResponse> jsonApi = new JsonApi<>("Something went wrong");
                    return new ResponseEntity<>(jsonApi, HttpStatus.BAD_REQUEST);
                });
    }
}

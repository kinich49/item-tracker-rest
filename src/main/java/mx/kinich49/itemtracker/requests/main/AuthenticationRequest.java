package mx.kinich49.itemtracker.requests.main;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String username;
    private String password;
}

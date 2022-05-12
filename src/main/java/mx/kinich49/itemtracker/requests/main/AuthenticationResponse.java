package mx.kinich49.itemtracker.requests.main;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthenticationResponse {

    private final String jwt;
}

package mx.kinich49.itemtracker.controllers.mobile;

import lombok.RequiredArgsConstructor;
import mx.kinich49.itemtracker.JsonApi;
import mx.kinich49.itemtracker.models.User;
import mx.kinich49.itemtracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("mobileUserController")
@RequestMapping("api/mobile/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<JsonApi<User>> getCategoryById(@PathVariable long id) {
        return userRepository.findById(id)
                .map(JsonApi::new)
                .map(json -> new ResponseEntity<>(json, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}

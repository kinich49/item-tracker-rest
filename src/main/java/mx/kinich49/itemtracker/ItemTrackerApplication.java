package mx.kinich49.itemtracker;

import mx.kinich49.itemtracker.requests.main.UserRequest;
import mx.kinich49.itemtracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ItemTrackerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ItemTrackerApplication.class, args);
    }

    @Autowired
    UserService userService;
    @Override
    public void run(String... args) throws Exception {
        var userRequest = new UserRequest();
        userRequest.setUsername("manager");
        userRequest.setPassword("password");

        userService.addUser(userRequest);
    }
}

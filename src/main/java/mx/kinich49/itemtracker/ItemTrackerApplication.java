package mx.kinich49.itemtracker;

import mx.kinich49.itemtracker.requests.main.UserRequest;
import mx.kinich49.itemtracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ItemTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemTrackerApplication.class, args);
    }
}

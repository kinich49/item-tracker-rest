package mx.kinich49.itemtracker.configurations;

import mx.kinich49.itemtracker.utils.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@Configuration
public class PasswordEncoderConfiguration {

    @Bean
    @Profile({"azure", "prod"})
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(31);
    }

    @Bean
    @Profile({"development", "test"})
    public PasswordEncoder dummyPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}

package com.henry.newmongodbapi;

import com.henry.newmongodbapi.models.User;
import com.henry.newmongodbapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.henry.newmongodbapi.models.Role.ADMIN;
import static com.henry.newmongodbapi.models.Role.USER;

@SpringBootApplication
@ConfigurationPropertiesScan("com.henry.newmongodbapi.util")
public class NewMongoDbapiApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(NewMongoDbapiApplication.class, args);
    }

    @Override
    public void run(String... args) {
        User adminAccount = userRepository.findByRole(ADMIN);

        if (adminAccount == null) {
            User admin = new User();
            admin.setUsername("root");
            admin.setPassword(new BCryptPasswordEncoder().encode("root"));
            admin.setStatus((short) 1);
            admin.setRole(ADMIN);
            admin.setIsAccountExpired((short) 0);
            admin.setIsAccountLocked((short) 0);
            admin.setIsCredentialsExpired((short) 0);

            userRepository.save(admin);
        }
    }
}

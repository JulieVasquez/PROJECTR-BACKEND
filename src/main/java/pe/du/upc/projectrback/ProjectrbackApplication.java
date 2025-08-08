package pe.du.upc.projectrback;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pe.du.upc.projectrback.entities.Authority;
import pe.du.upc.projectrback.entities.User;
import pe.du.upc.projectrback.services.AuthorityService;
import pe.du.upc.projectrback.services.UserService;

import java.util.List;

@SpringBootApplication
public class ProjectrbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectrbackApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(UserService userService,
                                      AuthorityService authorityService
                                      ) {
        return (args) -> {
            //creo roles
            Authority authority1 = authorityService.addAuthority(new Authority("ROLE_ADMIN"));
            Authority authority2 = authorityService.addAuthority(new Authority("ROLE_USER"));

            //creo usuarios
            User user1 = new User("rominaS","0201",true);
            User user2 = new User("julixns","pass",true);

            //asigno roles
            user1.setAuthorities(List.of(authority2));
            user2.setAuthorities(List.of(authority1));
            userService.addUser(user1);
            userService.addUser(user2);
        };
    }

}

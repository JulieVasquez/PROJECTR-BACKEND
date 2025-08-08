package pe.du.upc.projectrback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import pe.du.upc.projectrback.dtos.DTOToken;
import pe.du.upc.projectrback.entities.User;
import pe.du.upc.projectrback.security.JwtUtilService;
import pe.du.upc.projectrback.security.UserSecurity;
import pe.du.upc.projectrback.services.UserService;


import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/romip")
//http://localhost:8080/romip/users/login...

public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtilService jwtUtilService;


    @PostMapping("/users/login")
    public ResponseEntity<DTOToken> login(@RequestBody User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
        );
        UserSecurity userSecurity = (UserSecurity) userDetailsService.loadUserByUsername(user.getUsername());

        String jwt = jwtUtilService.generateToken(userSecurity);
        Long id = userSecurity.getUser().getUserId();
        String authorities = userSecurity.getUser().getAuthorities()
                .stream()
                .map(a->a.getName())
                .collect(Collectors.joining(";","",""));


        return new ResponseEntity<>(new DTOToken(jwt,id,authorities), HttpStatus.OK);
    }

    //para llamar username
    @GetMapping("/users/{userid}")
    public ResponseEntity<User> getUserById(@PathVariable Long userid) {
        User user = userService.findById(userid);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

package pe.du.upc.projectrback.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    private static final String[] AUTH_WHITELIST = {
            "/romip/users/login/**",

    };

    /*
    Authentication Manager
     */

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /*
    Security Filter
    1. Request que seran evaluados
        a. AnyRequest -> todos
        b. RequestMatchers -> Se evalua coincidencia
        c. RequestMatches+HtppMethod -> coincidencia con ruta y metodo (GET, POST, ETC)

    2. Permisos
        a. permitAll()
        b. denyALL()
        c. requestMatchers()
        d. hasRole()
        e. hasAnyRole()
        f. hasAuthority()
        h. hasAnyAuthority()
        g. authenticated()
     */
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(Customizer.withDefaults());

        http.authorizeHttpRequests( (auth)-> auth
                //julixns
                //pass
                //.anyRequest().permitAll()
                .requestMatchers(AUTH_WHITELIST).permitAll()




                .anyRequest().authenticated()
        );


        http.sessionManagement(
                (session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        return http.build();
    }
    /*
    Password Encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

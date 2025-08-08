package pe.du.upc.projectrback.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.du.upc.projectrback.entities.User;
import pe.du.upc.projectrback.security.UserSecurity;
import pe.du.upc.projectrback.services.UserService;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User usuarioF= userService.findByUsername(username);

        return new UserSecurity(usuarioF);
    }
}

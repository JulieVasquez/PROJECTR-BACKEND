package pe.du.upc.projectrback.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.du.upc.projectrback.entities.Authority;
import pe.du.upc.projectrback.entities.User;
import pe.du.upc.projectrback.exceptions.KeyRepeatedDataException;
import pe.du.upc.projectrback.exceptions.ResourceNotFoundException;
import pe.du.upc.projectrback.repositories.UserRepository;
import pe.du.upc.projectrback.services.AuthorityService;
import pe.du.upc.projectrback.services.UserService;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityService authorityService;

    private List<Authority> authoritiesFromString(String authorities){
        List<Authority> authorityList = new ArrayList<>();
        List<String> authoritiesStringList = Arrays.stream(authorities.split(";")).toList();
        for (String authorityString : authoritiesStringList) {
            Authority authority = authorityService.findByName(authorityString);

            if (authority != null) {
                authorityList.add(authority);
            }
        }
        return authorityList;
    }

    @Override
    public User findByUsername(String username) {
        User usuarioF = userRepository.findByUsername(username);
        if (usuarioF == null) {
            throw new ResourceNotFoundException("Usuario con username: " + username + " no encontrado.");
        }
        return usuarioF;
    }



    @Override
    public User addUser(User user) {
        User usuarioF = userRepository.findByUsername(user.getUsername());
        if (usuarioF != null) {
            throw  new KeyRepeatedDataException("El usuario con username: " + user.getUsername() + " ya existe.");
        }
        //1 valido us y pw cumplan con requisitos
        //2 encriptar pw
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        //3 Actualizar campos adicionales
        user.setEnabled(true);
        return userRepository.save(user);
    }


    //username en front
    @Override
    public User findById(Long userid) {
        return userRepository.findById(userid).orElse(null);
    }

}

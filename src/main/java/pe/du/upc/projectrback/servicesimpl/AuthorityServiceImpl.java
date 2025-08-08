package pe.du.upc.projectrback.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.du.upc.projectrback.entities.Authority;
import pe.du.upc.projectrback.repositories.AuthorityRepository;
import pe.du.upc.projectrback.services.AuthorityService;


@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority addAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public Authority findByName(String authorityName) {
        return authorityRepository.findByName(authorityName);
    }
}

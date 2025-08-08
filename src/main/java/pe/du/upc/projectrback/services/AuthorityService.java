package pe.du.upc.projectrback.services;


import pe.du.upc.projectrback.entities.Authority;

public interface AuthorityService {
    public Authority addAuthority(Authority authority);
    public Authority findByName(String authorityName);
}

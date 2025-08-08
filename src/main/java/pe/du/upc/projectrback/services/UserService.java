package pe.du.upc.projectrback.services;


import pe.du.upc.projectrback.entities.User;

public interface UserService {
    public User findByUsername(String username);
    public User addUser(User user);

    //importante para sacar el username en front
    public User findById(Long userid);
}

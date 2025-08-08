package pe.du.upc.projectrback.repositories;

import org.springframework.data.repository.CrudRepository;
import pe.du.upc.projectrback.entities.User;


public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);

}

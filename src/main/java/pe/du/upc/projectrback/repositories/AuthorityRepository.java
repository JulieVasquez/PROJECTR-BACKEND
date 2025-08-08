package pe.du.upc.projectrback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.du.upc.projectrback.entities.Authority;

public interface
AuthorityRepository extends JpaRepository<Authority, Long> {
    public Authority findByName(String name);

}

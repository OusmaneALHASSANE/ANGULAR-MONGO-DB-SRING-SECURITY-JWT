package ousmane.alhassane.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ousmane.alhassane.entities.AppUser;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    public  AppUser findByUsername(String username);
}










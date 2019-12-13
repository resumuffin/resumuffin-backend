package in.resumuff.core.users.db;

import in.resumuff.core.users.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Short> {
    
    Role findByName(String name);
    
}
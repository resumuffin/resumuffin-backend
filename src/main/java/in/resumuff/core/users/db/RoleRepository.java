package in.resumuff.core.users.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.resumuff.core.users.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Short> {
    Role findByName(String name);
}
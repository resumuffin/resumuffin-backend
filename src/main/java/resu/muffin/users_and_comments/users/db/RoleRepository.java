package resu.muffin.users_and_comments.users.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import resu.muffin.users_and_comments.users.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Short> {
    Role findByName(String name);
}
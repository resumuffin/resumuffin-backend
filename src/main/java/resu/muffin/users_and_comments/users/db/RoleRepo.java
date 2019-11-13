package resu.muffin.users_and_comments.users.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import resu.muffin.users_and_comments.users.entities.Role;

@Repository
public interface RoleRepo extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
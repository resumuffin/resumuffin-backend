package resu.muffin.users_and_comments.users.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import resu.muffin.users_and_comments.users.entities.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    
}
package in.resumuff.core.users.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import in.resumuff.core.users.entities.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    List<User> findAllByOrderByIdAsc();

    List<User> findAllBy(Pageable pageable);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
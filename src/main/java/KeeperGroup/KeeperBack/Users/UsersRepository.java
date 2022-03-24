package KeeperGroup.KeeperBack.Users;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Long>{
    List<User> findByUsername(String username, Sort id);
    List<User>findAll();
    User getByUsername(String username);
}

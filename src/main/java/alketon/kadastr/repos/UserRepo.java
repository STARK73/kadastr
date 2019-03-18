package alketon.kadastr.repos;

import alketon.kadastr.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
}

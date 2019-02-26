package alketon.kadastr.repos;

import alketon.kadastr.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {
}

package alketon.kadastr.repos;

import alketon.kadastr.models.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Integer> {
}

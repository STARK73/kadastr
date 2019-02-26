package alketon.kadastr.repos;

import alketon.kadastr.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepo extends JpaRepository<Contract, Long> {
}

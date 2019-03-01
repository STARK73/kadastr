package alketon.kadastr.repos;

import alketon.kadastr.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepo extends JpaRepository<Contract, Long> {
    List<Contract> findByAddressObjectContainingAndFamilyNameContains(String address, String familyName);
}

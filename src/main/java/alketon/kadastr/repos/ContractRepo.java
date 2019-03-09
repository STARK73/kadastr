package alketon.kadastr.repos;

import alketon.kadastr.models.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepo extends JpaRepository<Contract, Long> {

    Page<Contract> findAll(Pageable pageable);

    List<Contract> findByAddressObjectContainingAndFamilyNameContains(String address, String familyName);
}

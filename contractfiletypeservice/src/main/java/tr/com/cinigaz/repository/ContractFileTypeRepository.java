package tr.com.cinigaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.cinigaz.entity.ContractFileTypeEntity;

public interface ContractFileTypeRepository extends JpaRepository<ContractFileTypeEntity,Short> {
}

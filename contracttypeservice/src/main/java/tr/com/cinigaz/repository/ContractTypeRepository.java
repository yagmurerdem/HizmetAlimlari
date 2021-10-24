package tr.com.cinigaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.cinigaz.entity.ContractTypeEntity;


@Repository
public interface ContractTypeRepository extends JpaRepository<ContractTypeEntity,Short> {

}

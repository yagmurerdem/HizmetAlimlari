package tr.com.cinigaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.cinigaz.entity.ServiceTypeEntity;

@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceTypeEntity,Short> {

}

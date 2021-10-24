package tr.com.cinigaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.cinigaz.entity.EmployerEntity;

@Repository

public interface EmployerRepository extends JpaRepository<EmployerEntity,Short> {
}

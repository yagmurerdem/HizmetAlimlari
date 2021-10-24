package tr.com.cinigaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.cinigaz.entity.RecourceEntity;

public interface RecourceRepository extends JpaRepository<RecourceEntity,Short> {
}

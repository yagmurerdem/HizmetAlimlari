package tr.com.cinigaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.cinigaz.entity.TenderEntity;

public interface TenderRepository extends JpaRepository<TenderEntity,Short> {
}

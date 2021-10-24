package tr.com.cinigaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.cinigaz.entity.PaymentPlanEntity;
@Repository
public interface PaymentPlanRepository extends JpaRepository<PaymentPlanEntity,Short> {
    PaymentPlanEntity findByPaymentPlanId(short paymentPlanId);
}

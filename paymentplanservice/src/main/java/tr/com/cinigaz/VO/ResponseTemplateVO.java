package tr.com.cinigaz.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.cinigaz.entity.PaymentPlanEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ResponseTemplateVO {

    private PaymentPlanEntity paymentPlanEntity;
    private ContractEntity contract;
}

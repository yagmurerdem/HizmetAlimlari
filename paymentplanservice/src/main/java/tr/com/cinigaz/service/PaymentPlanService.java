package tr.com.cinigaz.service;

import org.springframework.data.domain.Page;
import tr.com.cinigaz.VO.ResponseTemplateVO;
import tr.com.cinigaz.dto.PaymentPlanDto;
import tr.com.cinigaz.entity.PaymentPlanEntity;
import tr.com.cinigaz.result.DataResult;
import tr.com.cinigaz.result.Result;

import java.awt.print.Pageable;
import java.util.List;

public interface PaymentPlanService {
    Result paymentPlanAdd(PaymentPlanEntity paymentPlanEntity);
    DataResult paymentPlanGetById(short paymentPlanId);
    DataResult <Page<PaymentPlanDto>> paymentPlanGetAll(Pageable pageable);
    DataResult paymentPlanUpdate (short paymentPlanId, PaymentPlanDto paymentPlanDto);
    Result paymentPlanDelete(short paymentPlanId);

    ResponseTemplateVO getPaymentPlanWithContract(short paymentPlanId);
}

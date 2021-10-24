package tr.com.cinigaz.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.cinigaz.VO.ResponseTemplateVO;
import tr.com.cinigaz.dto.PaymentPlanDto;
import tr.com.cinigaz.entity.PaymentPlanEntity;
import tr.com.cinigaz.service.PaymentPlanService;

import javax.validation.Valid;

@RestController
@RequestMapping(value ="/paymentplan")

public class PaymentPlanController {


    private PaymentPlanService paymentPlanService;

    @Autowired
    public PaymentPlanController(PaymentPlanService paymentPlanService) {
        this.paymentPlanService = paymentPlanService;
    }




    @PostMapping(value="/add")
    public ResponseEntity<?> paymentPlanAdd(@Valid @RequestBody PaymentPlanEntity paymentPlanEntity)
    {
       return ResponseEntity.ok(paymentPlanService.paymentPlanAdd(paymentPlanEntity));
    }

    @GetMapping(value="/getById/{payment_plan_id}")
    public ResponseEntity<?> paymentPlanGetById(@PathVariable("payment_plan_id") @Valid short paymentPlanId )
    {
        return  ResponseEntity.ok(paymentPlanService.paymentPlanGetById(paymentPlanId));
    }

    @GetMapping(value="/getAll")
    public ResponseEntity<?> paymentPlanGetAll (int pageNo,int pageSize)
    {
        return ResponseEntity.ok(paymentPlanService.paymentPlanGetAll(pageNo,pageSize));
    }

    @PutMapping(value="/update/{payment_plan_id}")
    public ResponseEntity<?> paymentPlanUpdate(@PathVariable ("payment_plan_id") @Valid short paymentPlanId, @RequestBody PaymentPlanDto paymentPlanDto)
    {
        return ResponseEntity.ok(paymentPlanService.paymentPlanUpdate(paymentPlanId,paymentPlanDto));
    }

    @DeleteMapping(value="/delete/{payment_plan_id}")
    public ResponseEntity<?> paymentPlanDelete(@PathVariable("payment_plan_id") @Valid short paymentPlanId)
    {
        return ResponseEntity.ok(paymentPlanService.paymentPlanDelete(paymentPlanId));
    }

    @GetMapping(value="/getPaymentPlanWithContract/{payment_plan_id}")
    public ResponseTemplateVO getPaymentPlanWithContract(@PathVariable("payment_plan_id") @Valid short paymentPlanId){
        return paymentPlanService. getPaymentPlanWithContract(paymentPlanId);
    }

}


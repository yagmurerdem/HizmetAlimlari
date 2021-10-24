package tr.com.cinigaz.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tr.com.cinigaz.VO.ContractEntity;
import tr.com.cinigaz.VO.ResponseTemplateVO;
import tr.com.cinigaz.dto.PaymentPlanDto;
import tr.com.cinigaz.entity.PaymentPlanEntity;
import tr.com.cinigaz.repository.PaymentPlanRepository;
import tr.com.cinigaz.result.*;
import tr.com.cinigaz.service.PaymentPlanService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class PaymentPlanServiceImpl implements PaymentPlanService {


    @Autowired
    private PaymentPlanRepository paymentPlanRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RestTemplate restTemplate;

    public PaymentPlanServiceImpl(PaymentPlanRepository paymentPlanRepository, ModelMapper modelMapper) {
        this.paymentPlanRepository = paymentPlanRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Result paymentPlanAdd(PaymentPlanEntity paymentPlanEntity) {
      //  PaymentPlanEntity paymentPlanEntity = modelMapper.map(paymentPlanEntity, PaymentPlanEntity.class);
        paymentPlanEntity.setCreateBy("yağmur"); // TODO : Yetki yapısında kulanıcı gelecek.
        paymentPlanEntity.setCreateAt(new Date());
        PaymentPlanEntity paymentPlanEntityResponse = this.paymentPlanRepository.save(paymentPlanEntity);
        if(paymentPlanEntityResponse !=null)
        {
            return new SuccessResult("Veri Eklendi");
        }
        else{
           return new ErrorResult("Veri eklenemedi");
        }


    }

    @Override
    public DataResult paymentPlanGetById(short paymentPlanId) {
       Optional<PaymentPlanEntity> paymentPlanEntityResponse = paymentPlanRepository.findById(paymentPlanId);

       if(paymentPlanEntityResponse.isPresent())
       {

         //  PaymentPlanEntity paymentPlanEntity =modelMapper.map(paymentPlanEntityResponse.get(), PaymentPlanDto.class);
           return new SuccessDataResult<PaymentPlanEntity>("Data Bulundu", paymentPlanEntityResponse.get());

       }
       else{
           return new ErrorDataResult<PaymentPlanDto>("Data Bulunamadı");
       }

    }

    @Override
    public DataResult<Page<PaymentPlanDto>> paymentPlanGetAllByPage(Pageable pageable) {
        //PageRequest pageable= PageRequest.of(pageNo-1,pageSize);
        List<PaymentPlanEntity> paymentPlanEntities= paymentPlanRepository.findAll(pageable).getContent();
        Page<PaymentPlanDto> dtos=paymentPlanEntities.stream().map(paymentplan ->modelMapper.map(paymentplan, PaymentPlanDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<Page<PaymentPlanDto>>("Veriler Listelendi",dtos);

    }

    @Override
    public DataResult paymentPlanUpdate(short paymentPlanId, PaymentPlanDto paymentPlanDto) {
        Optional<PaymentPlanEntity> paymentPlanEntityResponse = paymentPlanRepository.findById(paymentPlanId);
        if(paymentPlanEntityResponse.isPresent())
        {
            paymentPlanEntityResponse.get().setPaymentPlanName(paymentPlanDto.getPaymentPlanName());
            paymentPlanEntityResponse.get().setPaymentPlanDescription(paymentPlanDto.getPaymentPlanDescription());
            PaymentPlanEntity paymentPlanEntitySave = paymentPlanRepository.save(paymentPlanEntityResponse.get());
            PaymentPlanDto paymentPlanDtoResponse =modelMapper.map(paymentPlanEntitySave, PaymentPlanDto.class);
            return new SuccessDataResult<PaymentPlanDto>("Data Güncellendi", paymentPlanDtoResponse);
        }
        else
        {
            return new ErrorDataResult<PaymentPlanDto>("Data Güncelleme İşlemi Başarısız Oldu");
        }

    }

    @Override
    public Result paymentPlanDelete(short paymentPlanId) {
        Optional<PaymentPlanEntity> paymentPlanResponse= paymentPlanRepository.findById(paymentPlanId);
        if(paymentPlanResponse.isPresent())
        {
            paymentPlanRepository.deleteById(paymentPlanId);
            return new SuccessResult("Data Silme İşlemi Başarılı Şekilde Gerçekleştirildi");
        }
        else
        {
            return new ErrorResult("Data Silinemedi");
        }
    }

    @Override
    public ResponseTemplateVO getPaymentPlanWithContract(short paymentPlanId) {
        ResponseTemplateVO vo=new ResponseTemplateVO();
        PaymentPlanEntity paymentPlanEntity=paymentPlanRepository.findByPaymentPlanId(paymentPlanId);
        System.out.println();
        ContractEntity contract=restTemplate.getForObject("http://localhost:9046/contract/getById/"+ paymentPlanEntity.getContractId(), ContractEntity.class);
        vo.setPaymentPlanEntity(paymentPlanEntity);
        vo.setContract(contract);
        return vo;
    }


}

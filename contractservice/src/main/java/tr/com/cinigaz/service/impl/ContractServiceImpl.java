package tr.com.cinigaz.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.cinigaz.dto.ContractDto;
import tr.com.cinigaz.entity.ContractEntity;
import tr.com.cinigaz.repository.ContractRepository;
import tr.com.cinigaz.result.*;
import tr.com.cinigaz.service.ContractService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class ContractServiceImpl implements ContractService {



    @Autowired
    private ContractRepository contractRepository;
    private ModelMapper modelMapper;

    public ContractServiceImpl(ContractRepository contractRepository, ModelMapper modelMapper) {
        this.contractRepository = contractRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Result contractAdd(ContractEntity contractEntity) {
       // ContractEntity contractEntityResponse = modelMapper.map(contractDto, ContractEntity.class);
        contractEntity.setCreateBy("yağmur"); // TODO : Yetki yapısında kulanıcı gelecek.
        contractEntity.setCreateAt(new Date());
        ContractEntity contractEntityResponse = contractRepository.save(contractEntity);
        if (contractEntity != null) {
            return new SuccessResult("Veri  Ekleme İşlemi Başarılı Bir Şekilde Gerçekleştirildi");
        }
        else
        {
            return new ErrorResult("Veri  Ekleme İşlemi Gerçekleştirilemedi");
        }
    }

    @Override
    public DataResult<List<ContractDto>>contractGetAll(int pageNo, int pageSize) {
        Pageable pageable= PageRequest.of(pageNo-1,pageSize);
        List<ContractEntity> contractEntityList=contractRepository.findAll(pageable).getContent();
        List<ContractDto> dtos=contractEntityList.stream().map(contract ->modelMapper.map(contract,ContractDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ContractDto>>(dtos,"Veriler Listelendi");
    }

    @Override
    public DataResult contractGetById(short contractId) {
        Optional<ContractEntity> contractEntityOptional=contractRepository.findById(contractId);
        if(contractEntityOptional.isPresent())
        {
            ContractDto contractDto=modelMapper.map(contractEntityOptional.get(),ContractDto.class);
            return new SuccessDataResult<ContractDto>(contractDto,"Veri  Bulundu");
        }
        else
        {
            return new ErrorDataResult<>("Veri  Bulunamadı");
        }

    }

    @Override
    public DataResult contractUpdate(short contractId, ContractDto contractDto) {
        Optional<ContractEntity>  contractEntityResponse = contractRepository.findById(contractId);
        if(contractEntityResponse.isPresent())
        {
            contractEntityResponse.get().setContractName(contractDto.getContractName());
            contractEntityResponse.get().setContractDescription(contractDto.getContractDescription());
            contractEntityResponse.get().setUpdateAt(new Date());
            contractEntityResponse.get().setUpdateBy("yağmur");
            ContractEntity contractEntity=contractRepository.save(contractEntityResponse.get());
            ContractDto contractDtoResponse= modelMapper.map(contractEntity,ContractDto.class);
            return new SuccessDataResult<ContractDto>(contractDtoResponse,"Veri  Güncellendi");
        }
        else
        {
            return new ErrorDataResult<ContractDto>("Veri Güncellenemedi");
        }

    }

    @Override
    public Result contractDelete(short contractId) {
        Optional<ContractEntity>  contractEntityResponse = contractRepository.findById(contractId);
        if(contractEntityResponse.isPresent())
        {
            contractRepository.deleteById(contractId);
            return new SuccessResult("Veri Silindi");
        }
        else{
            return new ErrorResult("Veri Silinemedi");
        }

    }
}

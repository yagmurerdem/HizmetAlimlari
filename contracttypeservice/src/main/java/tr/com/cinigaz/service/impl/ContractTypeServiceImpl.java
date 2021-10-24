package tr.com.cinigaz.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.cinigaz.dto.ContractTypeDto;
import tr.com.cinigaz.entity.ContractTypeEntity;
import tr.com.cinigaz.repository.ContractTypeRepository;
import tr.com.cinigaz.result.*;
import tr.com.cinigaz.service.ContractTypeService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Configuration
public class ContractTypeServiceImpl implements ContractTypeService {

    private ContractTypeRepository contractTypeRepository;
    private ModelMapper modelMapper;

    public ContractTypeServiceImpl(ContractTypeRepository contractTypeRepository, ModelMapper modelMapper) {
        this.contractTypeRepository = contractTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Result contractTypeAdd(ContractTypeDto contractTypeDto) {
        ContractTypeEntity contractTypeEntity = modelMapper.map(contractTypeDto, ContractTypeEntity.class);
        contractTypeEntity.setCreateBy("yağmur"); // TODO : Yetki yapısında kulanıcı gelecek.
        contractTypeEntity.setCreateAt(new Date());
        this.contractTypeRepository.save(contractTypeEntity);
        return new SuccessResult("Ürün Eklendi");
    }

    @Override
    public DataResult<ContractTypeDto> contractTypeGetById(short contractTypeId) {
        Optional<ContractTypeEntity> contractTypeEntity = this.contractTypeRepository.findById(contractTypeId);
        if (contractTypeEntity.isPresent()) {
            ContractTypeDto contractTypeDto = modelMapper.map(contractTypeEntity.get(), ContractTypeDto.class);
            return new SuccessDataResult<ContractTypeDto>(contractTypeDto, "Data Bulundu");
        } else {
            return new ErrorDataResult("Data Bulunamadı");
        }
    }

    @Override
    public DataResult contractTypeUpdate(short contractTypeId, ContractTypeDto contractTypeDto) {
        Optional<ContractTypeEntity> contractTypeEntity = this.contractTypeRepository.findById(contractTypeId);
        if (contractTypeEntity.isPresent()) {
            contractTypeEntity.get().setContractTypeName(contractTypeDto.getContractTypeName());
            contractTypeEntity.get().setContractTypeDescription(contractTypeDto.getServiceTypeDescription());
            contractTypeEntity.get().setUpdateAt(new Date());
            contractTypeEntity.get().setUpdateBy("yağmur");
            ContractTypeDto contractTypeDto1 = modelMapper.map(contractTypeEntity.get(), ContractTypeDto.class);


           return new SuccessDataResult<ContractTypeDto>(contractTypeDto1, " Veri Güncellendi");
        }
        else {
            return new ErrorDataResult("Güncellenecek Veri Bulunamadı");
        }
    }

    @Override
    public Result contractTypeDelete(short contractTypeId) {
        Optional<ContractTypeEntity> contractTypeEntity = this.contractTypeRepository.findById(contractTypeId);
        if (contractTypeEntity.isPresent()) {
            this.contractTypeRepository.deleteById(contractTypeId);
           return new SuccessResult("Veri Silindi");

        }
        else {
            return new ErrorResult("Silinecek Veri Bulunamadı");
        }

    }

    @Override
    public DataResult<List<ContractTypeDto>> contractTypeGetAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<ContractTypeEntity> contractTypeEntity3 =this.contractTypeRepository.findAll(pageable).getContent();
        List<ContractTypeDto> dtos = contractTypeEntity3.stream().map(contracttype -> modelMapper.map(contracttype, ContractTypeDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ContractTypeDto>>( dtos,"Sayfalama Yapısına Göre Listelendi");//page türünde bir şey döndürür  onu okumak için get content yazarız;
    }
}

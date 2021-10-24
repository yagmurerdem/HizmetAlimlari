package tr.com.cinigaz.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.cinigaz.dto.ContractFileTypeDto;
import tr.com.cinigaz.entity.ContractFileTypeEntity;
import tr.com.cinigaz.repository.ContractFileTypeRepository;
import tr.com.cinigaz.result.*;
import tr.com.cinigaz.service.ContractFileTypeService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContractFileTypeServiceImpl implements ContractFileTypeService {

    private ContractFileTypeRepository contractFileTypeRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ContractFileTypeServiceImpl(ContractFileTypeRepository contractFileTypeRepository, ModelMapper modelMapper) {
        this.contractFileTypeRepository = contractFileTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Result contractFileTypeAdd(ContractFileTypeDto contractFileTypeDto) {
        ContractFileTypeEntity contractFileTypeEntity = modelMapper.map(contractFileTypeDto, ContractFileTypeEntity.class);
        contractFileTypeEntity.setCreateBy("yağmur");
        contractFileTypeEntity.setCreateAt(new Date());
        this.contractFileTypeRepository.save(contractFileTypeEntity);
        return new SuccessResult("Veri Eklendi");
    }

    @Override
    public DataResult contractFileTypeUpdate(short contractFileTypeId, ContractFileTypeDto contractFileTypeDto) {
        Optional<ContractFileTypeEntity> contractFileTypeEntity = this.contractFileTypeRepository.findById(contractFileTypeId);
        if (contractFileTypeEntity.isPresent()) {
            contractFileTypeEntity.get().setContractFileTypeName(contractFileTypeDto.getContractFileTypeName());
            contractFileTypeEntity.get().setContractFileTypeDescription(contractFileTypeDto.getContractFileTypeDescription());
            contractFileTypeEntity.get().setUpdateAt(new Date());
            contractFileTypeEntity.get().setUpdateBy("yağmur");
            ContractFileTypeDto contractFileTypeDto1 = modelMapper.map(this.contractFileTypeRepository.save(contractFileTypeEntity.get()), ContractFileTypeDto.class);
            return new SuccessDataResult<ContractFileTypeDto>(contractFileTypeDto1, "Veri Güncellendi");
        } else {
            return new ErrorDataResult("Veri Güncellenemedi");
        }
    }

    @Override
    public Result contractFileTypeDelete(short contractFileTypeId) {
        Optional<ContractFileTypeEntity> contractFileTypeEntity = this.contractFileTypeRepository.findById(contractFileTypeId);
        if (contractFileTypeEntity.isPresent()) {
            this.contractFileTypeRepository.deleteById(contractFileTypeId);
            return new SuccessResult("Veri Silindi");
        } else {
            return new ErrorResult("Veri Silinemedi");
        }


    }

    @Override
    public DataResult contractFileTypeGetById(short contractFileTypeId) {
        Optional<ContractFileTypeEntity> contractFileTypeEntity3 =this.contractFileTypeRepository.findById(contractFileTypeId);
        if(contractFileTypeEntity3.isPresent())
        {
            ContractFileTypeDto contractFileTypeDto1 = modelMapper.map(this.contractFileTypeRepository.save(contractFileTypeEntity3.get()), ContractFileTypeDto.class);
            return new SuccessDataResult<ContractFileTypeDto>(contractFileTypeDto1,"Veri Bulundu");
        }
        return null;
    }

    @Override
    public DataResult<List<ContractFileTypeDto>> contractFileTypeGetAll(int pageNo, int pageSize) {
        Pageable pageable= PageRequest.of(pageNo-1,pageSize);
       List<ContractFileTypeEntity> contractFileTypeEntity= this.contractFileTypeRepository.findAll(pageable).getContent();
        List<ContractFileTypeDto> dtos = contractFileTypeEntity.stream().map(contractfiletype -> modelMapper.map(contractfiletype, ContractFileTypeDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ContractFileTypeDto>>(dtos,"Veriler Listelendi");
    }
}

















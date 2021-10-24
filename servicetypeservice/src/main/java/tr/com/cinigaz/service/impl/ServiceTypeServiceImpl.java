package tr.com.cinigaz.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.cinigaz.dto.ServiceTypeDto;
import tr.com.cinigaz.entity.ServiceTypeEntity;
import tr.com.cinigaz.repository.ServiceTypeRepository;
import tr.com.cinigaz.result.*;
import tr.com.cinigaz.service.ServiceTypeService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Configuration
public class ServiceTypeServiceImpl implements ServiceTypeService {

    private ServiceTypeRepository serviceTypeRepository;
    private ModelMapper modelMapper;


    public ServiceTypeServiceImpl(ServiceTypeRepository serviceTypeRepository, ModelMapper modelMapper) {
        this.serviceTypeRepository = serviceTypeRepository;

        this.modelMapper = modelMapper;
    }

    @Override
    public Result serviceTypeAdd(ServiceTypeDto serviceTypeDto) {
        ServiceTypeEntity serviceTypeEntity = modelMapper.map(serviceTypeDto, ServiceTypeEntity.class);
        serviceTypeEntity.setCreateBy("yağmur"); // TODO : Yetki yapısında kulanıcı gelecek.
        serviceTypeEntity.setCreateAt(new Date());
        this.serviceTypeRepository.save(serviceTypeEntity);
        return new SuccessResult("Ürün eklendi");
    }

    @Override
    public DataResult<List<ServiceTypeDto>> serviceTypeGetAll(int pageNo, int pageSize) {
        Pageable pageable= PageRequest.of(pageNo-1,pageSize);
        List<ServiceTypeEntity> serviceTypeEntity= this.serviceTypeRepository.findAll(pageable).getContent();
        List<ServiceTypeDto> dtos = serviceTypeEntity.stream().map(servicetype -> modelMapper.map(servicetype , ServiceTypeDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ServiceTypeDto>>(dtos,"Veriler Listelendi");
    }


    @Override
    public DataResult<ServiceTypeDto> serviceTypeUpdate(short serviceTypeId, ServiceTypeDto serviceTypeDto) {
        Optional<ServiceTypeEntity> serviceTypeEntity = serviceTypeRepository.findById(serviceTypeId);
        if (serviceTypeEntity.isPresent()) {
            serviceTypeEntity.get().setServiceTypeName(serviceTypeDto.getServiceTypeName());
            serviceTypeEntity.get().setServiceTypeDescription(serviceTypeDto.getServiceTypeDescription());
            serviceTypeEntity.get().setUpdateAt(new Date());
            serviceTypeEntity.get().setUpdateBy("yağmur");
            ServiceTypeEntity serviceTypeEntityResponse= serviceTypeRepository.save(serviceTypeEntity.get());
            ServiceTypeDto serviceTypeDto1 = modelMapper.map(serviceTypeRepository.save(serviceTypeEntityResponse), ServiceTypeDto.class);
            return new SuccessDataResult<ServiceTypeDto>(serviceTypeDto1, "Data Güncellendi");
        } else {
            return new ErrorDataResult<ServiceTypeDto>("Data Güncelleme İşlemi Başarısız Oldu");
        }
    }

    @Override
    public Result serviceTypeDelete(short serviceTypeId) {
        Optional<ServiceTypeEntity> serviceTypeEntity = serviceTypeRepository.findById(serviceTypeId);
        if (serviceTypeEntity.isPresent()) {
            serviceTypeRepository.deleteById(serviceTypeId);
            return new SuccessResult("Data silindi");
        } else {
            return new ErrorResult("Data silinemedi");
        }

    }

    @Override
    public DataResult<ServiceTypeDto> serviceTypeGetById(short serviceTypeId) {
        Optional<ServiceTypeEntity> serviceTypeEntity = this.serviceTypeRepository.findById(serviceTypeId);
        if (serviceTypeEntity.isPresent()) {
            ServiceTypeDto serviceTypeDto2 = modelMapper.map(serviceTypeEntity.get(), ServiceTypeDto.class);
            return new SuccessDataResult<ServiceTypeDto>(serviceTypeDto2, "Data Bulundu");
        } else {
            return new ErrorDataResult<>("Data Bulunamadı");
        }
    }

   /* @Override
    public DataResult<List<ServiceTypeDto>> serviceTypeGetPage(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<ServiceTypeEntity> serviceTypeEntity = this.serviceTypeRepository.findAll(pageable).getContent();
        List<ServiceTypeDto> dtos = serviceTypeEntity.stream().map(servicetype -> modelMapper.map(servicetype, ServiceTypeDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ServiceTypeDto>>(dtos, "Data sayfalama yapısına göre listelendi");//page türünde bir şey döndürür  onu okumak için get content yazarız
    }*/


}

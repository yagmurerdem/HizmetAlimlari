package tr.com.cinigaz.entity.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.cinigaz.dto.EmployerDto;
import tr.com.cinigaz.entity.EmployerEntity;
import tr.com.cinigaz.entity.service.EmployerService;
import tr.com.cinigaz.repository.EmployerRepository;
import tr.com.cinigaz.result.*;

import java.util.Date;
import java.util.Optional;

@Service



public class EmployerServiceImpl implements EmployerService {

    private EmployerRepository employerRepository;
    private ModelMapper modelMapper;

    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository, ModelMapper modelMapper) {
        this.employerRepository = employerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Result employerAdd(EmployerDto employerDto) {
        EmployerEntity employerEntity = modelMapper.map(employerDto, EmployerEntity.class);
        employerEntity.setCreateBy("yağmur"); // TODO : Yetki yapısında kulanıcı gelecek.
        employerEntity.setCreateAt(new Date());
        employerRepository.save(employerEntity);
        return new SuccessResult("İşveren Eklendi") ;
    }

    @Override
    public Result employerDelete(short employerId) {
       Optional<EmployerEntity> employerEntity= employerRepository.findById(employerId);
       if(employerEntity.isPresent())
       {
           employerRepository.deleteById(employerId);
           return new SuccessResult("İşveren silindi");
       }
       else{
           return new ErrorResult("İşveren Silinemedi");
       }
    }

    @Override
    public DataResult employerUpdate(short employerId, EmployerDto employerDto) {
        Optional<EmployerEntity> employerEntity= employerRepository.findById(employerId);
        if(employerEntity.isPresent())
        {

            employerEntity.get().setEmployerName(employerDto.getEmployerName());
            employerEntity.get().setEmployerDescription(employerDto.getEmployerDescription());
            employerEntity.get().setUpdateAt(new Date());
            employerEntity.get().setUpdateBy("yağmur");
            EmployerDto employerDto1=modelMapper.map(employerRepository.save(employerEntity.get()), EmployerDto.class);
            return new SuccessDataResult<EmployerDto>(employerDto1,"İşveren Güncellendi");

        }
        else
        {
            return new ErrorDataResult<>("işveren Gücelleme işlemi yapılamadı");
        }

    }
}

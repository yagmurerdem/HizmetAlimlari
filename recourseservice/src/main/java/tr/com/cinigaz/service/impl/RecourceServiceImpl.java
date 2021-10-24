package tr.com.cinigaz.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.cinigaz.dto.RecourceDto;
import tr.com.cinigaz.entity.RecourceEntity;
import tr.com.cinigaz.repository.RecourceRepository;
import tr.com.cinigaz.result.*;
import tr.com.cinigaz.service.RecourceService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecourceServiceImpl implements RecourceService {

    private RecourceRepository recourceRepository;
    private ModelMapper modelMapper;

    @Autowired
    public RecourceServiceImpl(RecourceRepository recourceRepository, ModelMapper modelMapper) {
        this.recourceRepository = recourceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Result recourceAdd(RecourceDto recourceDto) {
        RecourceEntity recourceEntity = modelMapper.map(recourceDto, RecourceEntity.class);
        recourceEntity.setCreateBy("yağmur");
        recourceEntity.setCreateAt(new Date());
        this.recourceRepository.save(recourceEntity);
        return new SuccessResult("Veri Eklendi");
    }


    @Override
    public DataResult<RecourceDto> recourceUpdate(short recourceId, RecourceDto recourceDto) {
        Optional<RecourceEntity> recourceEntity = this.recourceRepository.findById(recourceId);
        if (recourceEntity.isPresent()) {
            recourceEntity.get().setRecourceName(recourceDto.getRecourceName());
            recourceEntity.get().setRecourceDescription(recourceDto.getRecourceDescription());
            recourceEntity.get().setUpdateAt(new Date());
            recourceEntity.get().setUpdateBy("yağmur");
            RecourceDto contractFileTypeDto1 = modelMapper.map(this.recourceRepository.save(recourceEntity.get()), RecourceDto.class);
            return new SuccessDataResult<RecourceDto>(contractFileTypeDto1, "Veri Güncellendi");
        } else {
            return new ErrorDataResult("Veri Güncellenemedi");
        }
    }

    @Override
    public Result recourceDelete(short recourceId) {
        Optional<RecourceEntity> recourceEntity = this.recourceRepository.findById(recourceId);
        if (recourceEntity.isPresent()) {
            this.recourceRepository.deleteById(recourceId);
            return new SuccessResult("Veri Silindi");
        } else {
            return new ErrorResult("Veri Silinemedi");
        }


    }

    @Override
    public DataResult recourceGetById(short recourceId) {
        Optional<RecourceEntity> recourceEntity = this.recourceRepository.findById(recourceId);
        if (recourceEntity.isPresent()) {
            RecourceDto recourceDto = modelMapper.map(this.recourceRepository.save(recourceEntity.get()), RecourceDto.class);
            return new SuccessDataResult<RecourceDto>(recourceDto, "Veri Bulundu");
        }
        return null;
    }

    @Override
    public DataResult<List<RecourceDto>> recourceGetAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<RecourceEntity> recourceEntity = this.recourceRepository.findAll(pageable).getContent();
        List<RecourceDto> dtos = recourceEntity.stream().map(recource -> modelMapper.map(recource, RecourceDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<RecourceDto>>(dtos, "Veriler Listelendi");
    }
}

















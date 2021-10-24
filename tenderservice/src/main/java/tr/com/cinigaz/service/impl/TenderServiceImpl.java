package tr.com.cinigaz.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.cinigaz.dto.TenderDto;
import tr.com.cinigaz.entity.TenderEntity;
import tr.com.cinigaz.repository.TenderRepository;
import tr.com.cinigaz.result.*;
import tr.com.cinigaz.service.TenderService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TenderServiceImpl implements TenderService {

    private TenderRepository tenderRepository;
    private ModelMapper modelMapper;

    @Autowired
    public TenderServiceImpl(TenderRepository tenderRepository, ModelMapper modelMapper) {
        this.tenderRepository = tenderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Result tenderAdd(TenderDto tenderDto) {
        TenderEntity tenderEntity = modelMapper.map(tenderDto, TenderEntity.class);
        tenderEntity.setCreateBy("yağmur");
        tenderEntity.setCreateAt(new Date());
        this.tenderRepository.save(tenderEntity);
        return new SuccessResult("Veri Eklendi");
    }


    @Override
    public DataResult<TenderDto> tenderUpdate(short tenderId, TenderDto tenderDto) {
        Optional<TenderEntity> tenderEntity = this.tenderRepository.findById(tenderId);
        if (tenderEntity.isPresent()) {
            tenderEntity.get().setTenderName(tenderDto.getTenderName());
            tenderEntity.get().setTenderDescription(tenderDto.getTenderDescription());
            tenderEntity.get().setUpdateAt(new Date());
            tenderEntity.get().setUpdateBy("yağmur");
            TenderDto tenderDto1 = modelMapper.map(this.tenderRepository.save(tenderEntity.get()), TenderDto.class);
            return new SuccessDataResult<TenderDto>(tenderDto1, "Veri Güncellendi");
        } else {
            return new ErrorDataResult("Veri Güncellenemedi");
        }
    }

    @Override
    public Result tenderDelete(short tenderId) {
        Optional<TenderEntity> tenderEntity = this.tenderRepository.findById(tenderId);
        if (tenderEntity.isPresent()) {
            this.tenderRepository.deleteById(tenderId);
            return new SuccessResult("Veri Silindi");
        } else {
            return new ErrorResult("Veri Silinemedi");
        }


    }

    @Override
    public DataResult tenderGetById(short tenderId) {
        Optional<TenderEntity> tenderEntity = this.tenderRepository.findById(tenderId);
        if (tenderEntity.isPresent()) {
            TenderDto tenderDto = modelMapper.map(this.tenderRepository.save(tenderEntity.get()), TenderDto.class);
            return new SuccessDataResult<TenderDto>(tenderDto, "Veri Bulundu");
        }
        return null;
    }

    @Override
    public DataResult<List<TenderDto>> tenderGetAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<TenderEntity> tenderEntity = this.tenderRepository.findAll(pageable).getContent();
        List<TenderDto> dtos = tenderEntity.stream().map(tender -> modelMapper.map(tender, TenderDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<TenderDto>>(dtos, "Veriler Listelendi");
    }
}

















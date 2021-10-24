package tr.com.cinigaz.service;

import tr.com.cinigaz.dto.ServiceTypeDto;
import tr.com.cinigaz.entity.ServiceTypeEntity;
import tr.com.cinigaz.result.DataResult;
import tr.com.cinigaz.result.Result;

import java.util.List;

public interface ServiceTypeService {
    Result serviceTypeAdd(ServiceTypeDto serviceTypeDto);
    DataResult<List<ServiceTypeDto>> serviceTypeGetAll(int pageNo,int pageSize);
    DataResult<ServiceTypeDto> serviceTypeUpdate(short serviceTypeId,ServiceTypeDto serviceTypeDto);
    Result serviceTypeDelete(short serviceTypeId);
    DataResult<ServiceTypeDto> serviceTypeGetById(short serviceTypeId);
    //DataResult<List<ServiceTypeDto>> serviceTypeGetPage(int pageNo,int pageSize);
}

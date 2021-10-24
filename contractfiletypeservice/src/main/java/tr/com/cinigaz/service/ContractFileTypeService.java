package tr.com.cinigaz.service;

import tr.com.cinigaz.dto.ContractFileTypeDto;
import tr.com.cinigaz.result.DataResult;
import tr.com.cinigaz.result.Result;

import java.util.List;

public interface ContractFileTypeService {

    Result contractFileTypeAdd(ContractFileTypeDto contractFileTypeDto);
    DataResult contractFileTypeUpdate(short contractFileTypeId, ContractFileTypeDto contractFileTypeDto);
    Result contractFileTypeDelete(short contractFileTypeId);
    DataResult contractFileTypeGetById(short contractFileTypeId);
    DataResult<List<ContractFileTypeDto>> contractFileTypeGetAll(int pageNo, int pageSize);
}

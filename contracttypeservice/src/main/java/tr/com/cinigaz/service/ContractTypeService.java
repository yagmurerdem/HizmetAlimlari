package tr.com.cinigaz.service;

import tr.com.cinigaz.dto.ContractTypeDto;
import tr.com.cinigaz.result.DataResult;
import tr.com.cinigaz.result.Result;

import java.util.List;

public interface ContractTypeService {
    Result contractTypeAdd(ContractTypeDto contractTypeDto);
    DataResult contractTypeGetById(short contractTypeId);
    DataResult contractTypeUpdate(short contractTypeId,ContractTypeDto contractTypeDto);
    Result contractTypeDelete(short contractTypeId);
    DataResult <List<ContractTypeDto>> contractTypeGetAll(int pageNo,int pageSize);
}

package tr.com.cinigaz.service;

import tr.com.cinigaz.dto.ContractDto;
import tr.com.cinigaz.entity.ContractEntity;
import tr.com.cinigaz.result.DataResult;
import tr.com.cinigaz.result.Result;

public interface ContractService {

    Result contractAdd(ContractEntity contractEntity);
    DataResult contractGetAll(int pageNo,int pageSize);
    DataResult contractGetById(short contractId);
    DataResult contractUpdate(short contractId, ContractDto contractDto);
    Result contractDelete(short contractId);
}

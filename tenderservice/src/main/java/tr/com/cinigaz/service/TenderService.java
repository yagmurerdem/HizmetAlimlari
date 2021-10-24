package tr.com.cinigaz.service;

import tr.com.cinigaz.dto.TenderDto;
import tr.com.cinigaz.result.DataResult;
import tr.com.cinigaz.result.Result;

import java.util.List;

public interface TenderService {

    Result tenderAdd(TenderDto tenderDto);
    DataResult tenderUpdate(short tenderId, TenderDto tenderDto);
    Result tenderDelete(short tenderId);
    DataResult tenderGetById(short recourceId);
    DataResult<List<TenderDto>> tenderGetAll(int pageNo, int pageSize);
}

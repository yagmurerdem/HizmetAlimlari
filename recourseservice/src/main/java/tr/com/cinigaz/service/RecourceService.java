package tr.com.cinigaz.service;

import tr.com.cinigaz.dto.RecourceDto;
import tr.com.cinigaz.result.DataResult;
import tr.com.cinigaz.result.Result;

import java.util.List;

public interface RecourceService {

    Result recourceAdd(RecourceDto recourceDto);
    DataResult recourceUpdate(short recourceId, RecourceDto recourceDto);
    Result recourceDelete(short recourceId);
    DataResult recourceGetById(short recourceId);
    DataResult<List<RecourceDto>> recourceGetAll(int pageNo, int pageSize);
}

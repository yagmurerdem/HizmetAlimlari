package tr.com.cinigaz.entity.service;

import tr.com.cinigaz.dto.EmployerDto;
import tr.com.cinigaz.result.DataResult;
import tr.com.cinigaz.result.Result;

public interface EmployerService {

    Result employerAdd(EmployerDto employerDto);
    Result employerDelete(short employerId);
    DataResult employerUpdate(short employerId,EmployerDto employerDto);
}

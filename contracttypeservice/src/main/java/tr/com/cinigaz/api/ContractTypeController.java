package tr.com.cinigaz.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import tr.com.cinigaz.dto.ContractTypeDto;
import tr.com.cinigaz.result.ErrorDataResult;
import tr.com.cinigaz.service.ContractTypeService;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/contracttype")

public class ContractTypeController {
    private ContractTypeService contractTypeService;


    @Autowired
    public ContractTypeController(ContractTypeService contractTypeService) {
        this.contractTypeService = contractTypeService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> contractTypeAdd(@RequestBody ContractTypeDto contractTypeDto) {
        return ResponseEntity.ok(this.contractTypeService.contractTypeAdd(contractTypeDto));
    }

    @GetMapping(value="/getById/{contract_type_id}")

    public ResponseEntity<?> contractTypeGetById(@PathVariable("contract_type_id") @Valid short contractTypeId ){
        return ResponseEntity.ok(this.contractTypeService.contractTypeGetById(contractTypeId));
    }

    @PutMapping(value="/update/{contract_type_id}")
    public ResponseEntity<?> contractTypeUpdate(@PathVariable("contract_type_id") @Valid short contractTypeId ,@RequestBody ContractTypeDto contractTypeDto)
    {
        return ResponseEntity.ok(this.contractTypeService.contractTypeUpdate(contractTypeId,contractTypeDto));
    }

    @DeleteMapping(value="/delete/{contract_type_id}")
    public ResponseEntity<?> contractTypeDelete(@PathVariable ("contract_type_id") @Valid short contractTypeId ){
        return ResponseEntity.ok(this.contractTypeService.contractTypeDelete(contractTypeId));
    }

    @GetMapping(value="/getAll")
    public ResponseEntity<?> contractTypeGetAll(int pageNo,int pageSize){
        return ResponseEntity.ok(this.contractTypeService.contractTypeGetAll(pageNo,pageSize));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions)
    {
        Map<String,String> validationErrors=new HashMap<String,String>();
        for(FieldError fieldError: exceptions.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errors= new ErrorDataResult<Object>(validationErrors,"Doğrulama Hataları");
        return errors;
    }



}

package tr.com.cinigaz.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.cinigaz.dto.ContractFileTypeDto;
import tr.com.cinigaz.service.ContractFileTypeService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/contractfiletype")

public class ContractFileTypeController {
    private ContractFileTypeService contractFileTypeService;

    @Autowired
    public ContractFileTypeController(ContractFileTypeService contractFileTypeService) {
        this.contractFileTypeService = contractFileTypeService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> contractFileTypeAdd(@RequestBody ContractFileTypeDto contractFileTypeDto){
        return ResponseEntity.ok(this.contractFileTypeService.contractFileTypeAdd(contractFileTypeDto));
    }

    @PutMapping(value="/update/{contract_file_type_id}")
    public ResponseEntity<?> contractFileTypeUpdate (@PathVariable("contract_file_type_id") @Valid short contractFileTypeId, @RequestBody ContractFileTypeDto contractFileTypeDto)
    {
        return ResponseEntity.ok(this.contractFileTypeService.contractFileTypeUpdate(contractFileTypeId,contractFileTypeDto));
    }
    @DeleteMapping(value="/delete/{contract_file_type_id}")
    public ResponseEntity<?> contractFileTypeDelete (@PathVariable("contract_file_type_id") @Valid short contractFileTypeId){
        return ResponseEntity.ok(this.contractFileTypeService.contractFileTypeDelete(contractFileTypeId));
    }

    @GetMapping(value="/getById/{contract_file_type_id}")
    public ResponseEntity<?> contractFileTypeGetById(@PathVariable("contract_file_type_id") @Valid short contractFileTypeId){
        return ResponseEntity.ok(this.contractFileTypeService.contractFileTypeGetById(contractFileTypeId));
    }

    @GetMapping(value="/getAll")
    public ResponseEntity<?> contractFileTypeGetAll(int pageNo,int pageSize){
        return ResponseEntity.ok(this.contractFileTypeService.contractFileTypeGetAll(pageNo,pageSize));
    }
}



















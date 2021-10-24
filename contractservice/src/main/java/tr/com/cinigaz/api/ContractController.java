package tr.com.cinigaz.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.cinigaz.dto.ContractDto;
import tr.com.cinigaz.entity.ContractEntity;
import tr.com.cinigaz.service.ContractService;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }



    @PostMapping(value="/add")
    public ResponseEntity<?> contractAdd(@Valid @RequestBody ContractEntity contractEntity)
    {
        return ResponseEntity.ok(contractService.contractAdd(contractEntity));
    }

    @GetMapping(value="/getAll")
    public ResponseEntity<?> contractGetAll(int pageNo,int pageSize)
    {
        return ResponseEntity.ok(contractService.contractGetAll(pageNo,pageSize));
    }

    @GetMapping(value="/getById/{contract_id}")
    public ResponseEntity<?> contractGetById(@PathVariable("contract_id")  @Valid short contractId)
    {
        return ResponseEntity.ok(contractService.contractGetById(contractId));
    }

    @PutMapping(value="/getUpdate/{contract_id}")
    public ResponseEntity<?> contractUpdate (@PathVariable("contract_id") @Valid short contractId,@RequestBody ContractDto contractDto )
    {
        return ResponseEntity.ok(contractService.contractUpdate(contractId,contractDto));
    }


    @DeleteMapping(value="/delete/{contract_id}")
    public ResponseEntity<?> contractDelete(@PathVariable("contract_id")  @Valid short contractId)
    {
        return ResponseEntity.ok(contractService.contractDelete(contractId));
    }
}

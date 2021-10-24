package tr.com.cinigaz.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.cinigaz.dto.TenderDto;
import tr.com.cinigaz.service.TenderService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/tender")

public class TenderController {
    private TenderService tenderService;
    @Autowired
    public TenderController(TenderService tenderService) {
        this.tenderService = tenderService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> tenderAdd(@RequestBody TenderDto tenderDto){
        return ResponseEntity.ok(this.tenderService.tenderAdd(tenderDto));
    }

    @PutMapping(value="/update/{tender_id}")
    public ResponseEntity<?> tenderUpdate (@PathVariable("tender_id") @Valid short tenderId, @RequestBody TenderDto tenderDto)
    {
        return ResponseEntity.ok(this.tenderService.tenderUpdate(tenderId, tenderDto));
    }

    @DeleteMapping(value="/delete/{tender_id}")
    public ResponseEntity<?> tenderDelete (@PathVariable("tender_id") @Valid short tenderId){
        return ResponseEntity.ok(this.tenderService.tenderDelete(tenderId));
    }

    @GetMapping(value="/getById/{tender_id}")
    public ResponseEntity<?> tenderGetById(@PathVariable("tender_id") @Valid short tenderId){
        return ResponseEntity.ok(this.tenderService.tenderGetById(tenderId));
    }

    @GetMapping(value="/getAll")
    public ResponseEntity<?> tenderGetAll(int pageNo,int pageSize){
        return ResponseEntity.ok(this.tenderService.tenderGetAll(pageNo,pageSize));
    }
}



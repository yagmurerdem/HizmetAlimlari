package tr.com.cinigaz.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.cinigaz.dto.RecourceDto;
import tr.com.cinigaz.service.RecourceService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/recource")

public class RecourceController {
    private RecourceService recourceService;
    @Autowired
    public RecourceController(RecourceService recourceService) {
        this.recourceService = recourceService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> recourceAdd(@RequestBody RecourceDto recourceDto){
        return ResponseEntity.ok(this.recourceService.recourceAdd( recourceDto));
    }

    @PutMapping(value="/update/{recource_id}")
    public ResponseEntity<?> recourceUpdate (@PathVariable("recource_id") @Valid short recourceId, @RequestBody RecourceDto recourceDto)
    {
        return ResponseEntity.ok(this.recourceService.recourceUpdate(recourceId,recourceDto));
    }

    @DeleteMapping(value="/delete/{recource_id}")
    public ResponseEntity<?> recourceDelete (@PathVariable("recource_id") @Valid short recourceId){
        return ResponseEntity.ok(this.recourceService.recourceDelete(recourceId));
    }

    @GetMapping(value="/getById/{recource_id}")
    public ResponseEntity<?> recourceGetById(@PathVariable("recource_id") @Valid short recourceId){
        return ResponseEntity.ok(this.recourceService.recourceGetById(recourceId));
    }

    @GetMapping(value="/getAll")
    public ResponseEntity<?> recourceGetAll(int pageNo,int pageSize){
        return ResponseEntity.ok(this.recourceService.recourceGetAll(pageNo,pageSize));
    }
}



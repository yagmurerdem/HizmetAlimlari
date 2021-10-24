package tr.com.cinigaz.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.cinigaz.dto.EmployerDto;
import tr.com.cinigaz.entity.service.EmployerService;
import tr.com.cinigaz.result.Result;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/employer")

public class EmployerController {

    private EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @PostMapping(value="/add")
    public ResponseEntity<?> employerAdd(@Valid @RequestBody EmployerDto employerDto){
        return ResponseEntity.ok(employerService.employerAdd(employerDto));

    }

    @DeleteMapping(value="/delete/{employer_id}")
    public ResponseEntity<?> employerDelete(@PathVariable("employer_id") @Valid short employerId){
        return ResponseEntity.ok(employerService.employerDelete(employerId));
    }

    @PutMapping(value="/update/{employer_id}")
    public ResponseEntity<?> employerUpdate(@PathVariable("employer_id") @Valid short employerId, @RequestBody EmployerDto employerDto)
    {
        return ResponseEntity.ok(employerService.employerUpdate(employerId,employerDto));
    }
}

package tr.com.cinigaz.api;

import org.springframework.asm.Handle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import tr.com.cinigaz.dto.ServiceTypeDto;
import tr.com.cinigaz.result.DataResult;
import tr.com.cinigaz.result.ErrorDataResult;
import tr.com.cinigaz.result.Result;
import tr.com.cinigaz.service.ServiceTypeService;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/servicetype")

public class ServiceTypeController {
    private ServiceTypeService serviceTypeService;

    @Autowired
    public ServiceTypeController(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?>  serviceTypeAdd(@Valid @RequestBody ServiceTypeDto serviceTypeDto) {
        return ResponseEntity.ok(this.serviceTypeService.serviceTypeAdd(serviceTypeDto));
    }


   /* @GetMapping(value = "/getAll")
    public ResponseEntity<?> serviceTypeGetAll() {
        return ResponseEntity.ok(this.serviceTypeService.serviceTypeGetAll());

    }*/

    @PutMapping(value = "/update/{service_type_id}")
    public ResponseEntity<?> serviceTypeUpdate(@PathVariable("service_type_id") @Valid short serviceTypeId, @RequestBody ServiceTypeDto serviceTypeDto) {
        return ResponseEntity.ok(this.serviceTypeService.serviceTypeUpdate(serviceTypeId, serviceTypeDto));
    }

    @DeleteMapping(value = "/delete/{service_type_id}")
    public ResponseEntity<?> serviceTypeDelete(@PathVariable("service_type_id") @Valid short serviceTypeId) {
        return ResponseEntity.ok(this.serviceTypeService.serviceTypeDelete(serviceTypeId));
    }

    @GetMapping(value = "/getById/{service_type_id}")
    public ResponseEntity<?> serviceTypeGetById(@PathVariable("service_type_id") @Valid short serviceTypeId) {
        return ResponseEntity.ok(this.serviceTypeService.serviceTypeGetById(serviceTypeId));
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<?> serviceTypeGetAll(int pageNo, int pageSize) {
        return ResponseEntity.ok(this.serviceTypeService.serviceTypeGetAll(pageNo, pageSize));
    }


    //sistemde bir hata olursa  handleValidationException bu metodu ??a????r oraya hatalar parametre olarak ge??ilir(exceptions) hatalar?? foreach le d??nd??m herbirini listeye ekleyip d??nd??rd??k
    @ExceptionHandler(ConstraintViolationException.class) //sistemde validation ile olan hatalar buraya d????er (do??rulama,required notnull,email)//sistemde bir hata olursa handleValidationException bu metodu ??a????r demek ve hatalar?? parametre olarak ge?? demek bu hatalar?? foreach ile d??nd??r??p listeye eklicez(Map<String,String>)
    @ResponseStatus(HttpStatus.BAD_REQUEST) //500 hatas??//bu method default olarak bad request d??ns??n dok??manda bu ??ekilde
    //public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions)//ne d??nece??ini nesnenin t??r?? her??ey olabilirbilmedi??imiz i??in object yazar??z.B??t??n classlar??n temeli object tiplidir(primitive tiplerinde (int , boolean .... stringlerinde)) )//b??t??n metodlar?? burdan ge??iricez //bu sistemle ????yle bi hata olursa bu metodu devreye sok diyoruz
    public ErrorDataResult<Object> handleValidationException(ConstraintViolationException exceptions)//ne d??nece??ini nesnenin t??r?? her??ey olabilirbilmedi??imiz i??in object yazar??z.B??t??n classlar??n temeli object tiplidir(primitive tiplerinde (int , boolean .... stringlerinde)) )//b??t??n metodlar?? burdan ge??iricez //bu sistemle ????yle bi hata olursa bu metodu devreye sok diyoruz
    { //hangi alanda hata oldu hata ne diye yaz??caz ??imdi
        Map<String,String> validationErrors=new HashMap<String,String>(); //Map<String,String> ilki hangi alan(email strin oldu??u i??in) ikincisimesaj de??eri string

        /*for(FieldError fieldError: exceptions.getBindingResult().getFieldErrors()){//user da olu??an o alanlar??n i??in t??m hatalar?? oku
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());//listeye ekledik
        }*/

        validationErrors.put("hata","de??er hatas??");
        ErrorDataResult<Object> errors= new ErrorDataResult<Object>(validationErrors,"Do??rulama Hatalar??"); // ??stte yazd??????m??z validation ?? ErrorDataResult a ??eviriyoruz d??nd??r??yoruz
        return errors;
    }


}








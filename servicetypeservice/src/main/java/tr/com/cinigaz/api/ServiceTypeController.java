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


    //sistemde bir hata olursa  handleValidationException bu metodu çağır oraya hatalar parametre olarak geçilir(exceptions) hataları foreach le döndüm herbirini listeye ekleyip döndürdük
    @ExceptionHandler(ConstraintViolationException.class) //sistemde validation ile olan hatalar buraya düşer (doğrulama,required notnull,email)//sistemde bir hata olursa handleValidationException bu metodu çağır demek ve hataları parametre olarak geç demek bu hataları foreach ile döndürüp listeye eklicez(Map<String,String>)
    @ResponseStatus(HttpStatus.BAD_REQUEST) //500 hatası//bu method default olarak bad request dönsün dokümanda bu şekilde
    //public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions)//ne döneceğini nesnenin türü herşey olabilirbilmediğimiz için object yazarız.Bütün classların temeli object tiplidir(primitive tiplerinde (int , boolean .... stringlerinde)) )//bütün metodları burdan geçiricez //bu sistemle şöyle bi hata olursa bu metodu devreye sok diyoruz
    public ErrorDataResult<Object> handleValidationException(ConstraintViolationException exceptions)//ne döneceğini nesnenin türü herşey olabilirbilmediğimiz için object yazarız.Bütün classların temeli object tiplidir(primitive tiplerinde (int , boolean .... stringlerinde)) )//bütün metodları burdan geçiricez //bu sistemle şöyle bi hata olursa bu metodu devreye sok diyoruz
    { //hangi alanda hata oldu hata ne diye yazıcaz şimdi
        Map<String,String> validationErrors=new HashMap<String,String>(); //Map<String,String> ilki hangi alan(email strin olduğu için) ikincisimesaj değeri string

        /*for(FieldError fieldError: exceptions.getBindingResult().getFieldErrors()){//user da oluşan o alanların için tüm hataları oku
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());//listeye ekledik
        }*/

        validationErrors.put("hata","değer hatası");
        ErrorDataResult<Object> errors= new ErrorDataResult<Object>(validationErrors,"Doğrulama Hataları"); // üstte yazdığımız validation ı ErrorDataResult a çeviriyoruz döndürüyoruz
        return errors;
    }


}








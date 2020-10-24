package it.leo.rendicontationplatform.controllers;


import it.leo.rendicontationplatform.entities.Service;
import it.leo.rendicontationplatform.services.ServiceService;
import it.leo.rendicontationplatform.support.Constants;
import it.leo.rendicontationplatform.support.exceptions.ServiceAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/services")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;


    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Service service) {
        try {
            Service added = serviceService.addService(service);
            return new ResponseEntity<>(added, HttpStatus.OK);
        } catch (ServiceAlreadyExistException e) {
            return new ResponseEntity<>(Constants.SERVICE_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity edit(@RequestBody @Valid Service service) {
        try {
            Service added = serviceService.editService(service);
            return new ResponseEntity<>(added, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Constants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
        }
    }


}

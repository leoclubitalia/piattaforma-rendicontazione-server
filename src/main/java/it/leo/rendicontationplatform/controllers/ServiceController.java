package it.leo.rendicontationplatform.controllers;


import it.leo.rendicontationplatform.entities.Service;
import it.leo.rendicontationplatform.services.ServiceService;
import it.leo.rendicontationplatform.support.Constants;
import it.leo.rendicontationplatform.support.Utils;
import it.leo.rendicontationplatform.support.exceptions.ServiceAlreadyExistException;
import it.leo.rendicontationplatform.support.exceptions.UnableToAddServiceForSomeoneElseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/services")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;


    @PreAuthorize("hasAuthority('club')")
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Service service) {
        try {
            Service added = serviceService.addService(Utils.getEmail(), service);
            return new ResponseEntity(added, HttpStatus.OK);
        }
        catch ( ServiceAlreadyExistException e ) {
            return new ResponseEntity(Constants.SERVICE_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
        }
        catch ( UnableToAddServiceForSomeoneElseException e ) {
            return new ResponseEntity(Constants.UNABLE_TO_ADD_FOR_SOMEONE_ELSE, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('club')")
    @PutMapping
    public ResponseEntity edit(@RequestBody @Valid Service service) {
        //return new ResponseEntity(serviceService.editService(service), HttpStatus.OK);
        return new ResponseEntity(null, HttpStatus.FORBIDDEN);
    }


}

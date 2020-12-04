package it.leo.rendicontationplatform.controllers;


import it.leo.rendicontationplatform.entities.Service;
import it.leo.rendicontationplatform.services.ServiceService;
import it.leo.rendicontationplatform.support.Constants;
import it.leo.rendicontationplatform.support.exceptions.ServiceAlreadyExistException;
import it.leo.rendicontationplatform.support.exceptions.UnableToAddServiceForSomeoneElseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.security.Principal;


@RestController
@RequestMapping("/services")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;


    @RolesAllowed("club")
    @PostMapping
    public ResponseEntity create(Principal principal, @RequestBody @Valid Service service) {
        try {
            Service added = serviceService.addService(principal.getName(), service);
            return new ResponseEntity(added, HttpStatus.OK);
        }
        catch ( ServiceAlreadyExistException e ) {
            return new ResponseEntity(Constants.SERVICE_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
        }
        catch ( UnableToAddServiceForSomeoneElseException e ) {
            return new ResponseEntity(Constants.UNABLE_TO_ADD_FOR_SOMEONE_ELSE, HttpStatus.BAD_REQUEST);
        }
    }

    @RolesAllowed("club")
    @PutMapping
    public ResponseEntity edit(Principal principal, @RequestBody @Valid Service service) {
        //return new ResponseEntity(serviceService.editService(service), HttpStatus.OK);
        return new ResponseEntity(null, HttpStatus.FORBIDDEN);
    }


}

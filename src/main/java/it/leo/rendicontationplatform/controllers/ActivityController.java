package it.leo.rendicontationplatform.controllers;


import it.leo.rendicontationplatform.entities.Activity;
import it.leo.rendicontationplatform.services.ActivityService;
import it.leo.rendicontationplatform.support.Constants;
import it.leo.rendicontationplatform.support.exceptions.ActivityAlreadyExistException;
import it.leo.rendicontationplatform.support.exceptions.UnableToAddActivityForSomeoneElseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.security.Principal;


@RestController
@RequestMapping("/activities")
public class ActivityController {
    @Autowired
    private ActivityService activityService;


    @RolesAllowed("club")
    @PostMapping
    public ResponseEntity create(Principal principal, @RequestBody @Valid Activity activity) {
        try {
            Activity added = activityService.addActivity(principal.getName(), activity);
            return new ResponseEntity(added, HttpStatus.OK);
        }
        catch ( ActivityAlreadyExistException e ) {
            return new ResponseEntity(Constants.ACTIVITY_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
        }
        catch ( UnableToAddActivityForSomeoneElseException e ) {
            return new ResponseEntity(Constants.UNABLE_TO_ADD_FOR_SOMEONE_ELSE, HttpStatus.BAD_REQUEST);
        }
    }

    @RolesAllowed("club")
    @PutMapping
    public ResponseEntity edit(@RequestBody @Valid Activity activity) {
        // return new ResponseEntity(activityService.editActivity(activity), HttpStatus.OK);
        return new ResponseEntity(null, HttpStatus.FORBIDDEN);
    }


}
package it.leo.rendicontationplatform.controllers;


import it.leo.rendicontationplatform.entities.Activity;
import it.leo.rendicontationplatform.services.ActivityService;
import it.leo.rendicontationplatform.support.Constants;
import it.leo.rendicontationplatform.support.authentication.Utils;
import it.leo.rendicontationplatform.support.exceptions.ActivityAlreadyExistException;
import it.leo.rendicontationplatform.support.exceptions.UnableToAddActivityForSomeoneElseException;
import it.leo.rendicontationplatform.support.exceptions.UnableToDeleteActivityForSomeoneElseException;
import it.leo.rendicontationplatform.support.exceptions.UnableToEditActivityForSomeoneElseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/activities")
public class ActivityController {
    @Autowired
    private ActivityService activityService;


    @PreAuthorize("hasAuthority('club')")
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Activity activity) {
        try {
            Activity added = activityService.addActivity(Utils.getEmail(), activity);
            return new ResponseEntity(added, HttpStatus.OK);
        }
        catch ( UnableToAddActivityForSomeoneElseException e ) {
            return new ResponseEntity(Constants.UNABLE_TO_ADD_FOR_SOMEONE_ELSE, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('club')")
    @PostMapping("/edit") // it should be a put but doesn't work with flutter web
    public ResponseEntity edit(@RequestBody @Valid Activity activity) {
        try {
            Activity edited = activityService.editActivity(Utils.getEmail(), activity);
            return new ResponseEntity(edited, HttpStatus.OK);
        }
        catch ( UnableToEditActivityForSomeoneElseException e ) {
            return new ResponseEntity(Constants.UNABLE_TO_EDIT_FOR_SOMEONE_ELSE, HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('club')")
    @GetMapping("/delete") // it should be a delete but doesn't work with flutter web
    public ResponseEntity delete(@RequestParam(required = false) Integer activityId) {
        try {
            Activity deleted = activityService.deleteActivity(Utils.getEmail(), activityId);
            return new ResponseEntity(deleted, HttpStatus.OK);
        }
        catch ( UnableToDeleteActivityForSomeoneElseException e ) {
            return new ResponseEntity(Constants.UNABLE_TO_DELETE_FOR_SOMEONE_ELSE, HttpStatus.BAD_REQUEST);
        }
    }


}
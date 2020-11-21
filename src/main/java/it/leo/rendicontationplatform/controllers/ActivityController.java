package it.leo.rendicontationplatform.controllers;


import it.leo.rendicontationplatform.entities.Activity;
import it.leo.rendicontationplatform.services.ActivityService;
import it.leo.rendicontationplatform.support.Constants;
import it.leo.rendicontationplatform.support.exceptions.ActivityAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/activities")
public class ActivityController {
    @Autowired
    private ActivityService activityService;


    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Activity activity) {
        //TODO get and put club id from principal
        try {
            Activity added = activityService.addActivity(activity);
            return new ResponseEntity(added, HttpStatus.OK);
        } catch (ActivityAlreadyExistException e) {
            return new ResponseEntity(Constants.SERVICE_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity edit(@RequestBody @Valid Activity activity) {
        return new ResponseEntity(activityService.editActivity(activity), HttpStatus.OK);
    }


}
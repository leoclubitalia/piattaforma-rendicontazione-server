package it.leo.rendicontationplatform.controllers;


import it.leo.rendicontationplatform.entities.Club;
import it.leo.rendicontationplatform.services.RetrieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/club")
public class ClubController {
    @Autowired
    private RetrieveService retrieveService;


    @GetMapping("/details")
    public ResponseEntity getInfoClub(@RequestParam(value = "name") String name) {
        return new ResponseEntity(retrieveService.getInfoClub(name), HttpStatus.OK);
    }

    @GetMapping("/quantity_services_made")
    public ResponseEntity getServicesQuantity(@RequestBody @Valid Club club) {
        return new ResponseEntity(retrieveService.getQuantityServices(club), HttpStatus.OK);
    }

    @GetMapping("/quantity_activities_made")
    public ResponseEntity getActivitiesQuantity(@RequestBody @Valid Club club) {
        return new ResponseEntity(retrieveService.getQuantityActivities(club), HttpStatus.OK);
    }


}
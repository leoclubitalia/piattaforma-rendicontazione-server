package it.leo.rendicontationplatform.controllers;


import it.leo.rendicontationplatform.services.RetrieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/club")
public class ClubController {
    @Autowired
    private RetrieveService retrieveService;


    @GetMapping("/details")
    public ResponseEntity getInfoClub(int id) {
        return new ResponseEntity(retrieveService.getInfoClub(id), HttpStatus.OK);
    }

    @GetMapping("/quantity_services_made")
    public ResponseEntity getServicesQuantity(int clubId) {
        return new ResponseEntity(retrieveService.getQuantityServices(clubId), HttpStatus.OK);
    }

    @GetMapping("/quantity_activities_made")
    public ResponseEntity getActivitiesQuantity(int clubId) {
        return new ResponseEntity(retrieveService.getQuantityActivities(clubId), HttpStatus.OK);
    }


}
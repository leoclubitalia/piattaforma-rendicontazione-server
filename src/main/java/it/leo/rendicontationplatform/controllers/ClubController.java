package it.leo.rendicontationplatform.controllers;


import it.leo.rendicontationplatform.services.ClubService;
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
    @Autowired
    private ClubService clubService;


    @GetMapping("/details")
    public ResponseEntity getInfoClub(int id) {
        //TODO get and put club id from principal
        return new ResponseEntity(retrieveService.getInfoClub(id), HttpStatus.OK);
    }

    @GetMapping("/quantity_services_made")
    public ResponseEntity getServicesQuantity(int clubId) {
        //TODO get and put club id from principal
        return new ResponseEntity(retrieveService.getQuantityServices(clubId), HttpStatus.OK);
    }

    @GetMapping("/quantity_activities_made")
    public ResponseEntity getActivitiesQuantity(int clubId) {
        //TODO get and put club id from principal
        return new ResponseEntity(retrieveService.getQuantityActivities(clubId), HttpStatus.OK);
    }

    @PutMapping("/update/quantity_current_members")
    public ResponseEntity updateCurrentMembersQuantity(int clubId, int newQuantity) {
        //TODO get and put club id from principal
        return new ResponseEntity(clubService.updateCurrentMembersQuantity(clubId, newQuantity), HttpStatus.OK);
    }

    @PutMapping("/update/quantity_aspirant_members")
    public ResponseEntity updateAspirantMembersQuantity(int clubId, int newQuantity) {
        //TODO get and put club id from principal
        return new ResponseEntity(clubService.updateAspirantMembersQuantity(clubId, newQuantity), HttpStatus.OK);
    }


}
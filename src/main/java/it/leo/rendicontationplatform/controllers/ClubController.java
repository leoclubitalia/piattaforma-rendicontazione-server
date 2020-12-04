package it.leo.rendicontationplatform.controllers;


import it.leo.rendicontationplatform.services.ClubService;
import it.leo.rendicontationplatform.services.RetrieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.RolesAllowed;
import java.security.Principal;


@RestController
@RequestMapping("/club")
public class ClubController {
    @Autowired
    private RetrieveService retrieveService;
    @Autowired
    private ClubService clubService;


    @RolesAllowed("club")
    @GetMapping("/details")
    public ResponseEntity getInfoClub(Principal principal) {
        return new ResponseEntity(retrieveService.getInfoClub(principal.getName()), HttpStatus.OK);
    }

    @RolesAllowed("club")
    @GetMapping("/quantity_services_made")
    public ResponseEntity getServicesQuantity(Principal principal) {
        return new ResponseEntity(retrieveService.getQuantityServices(principal.getName()), HttpStatus.OK);
    }

    @RolesAllowed("club")
    @GetMapping("/quantity_activities_made")
    public ResponseEntity getActivitiesQuantity(Principal principal) {
        return new ResponseEntity(retrieveService.getQuantityActivities(principal.getName()), HttpStatus.OK);
    }

    @RolesAllowed("club")
    @GetMapping("/update/quantity_current_members") // it should be a put but doesn't work with flutter web
    public ResponseEntity updateCurrentMembersQuantity(Principal principal, int newQuantity) {
        return new ResponseEntity(clubService.updateCurrentMembersQuantity(principal.getName(), newQuantity), HttpStatus.OK);
    }

    @RolesAllowed("club")
    @GetMapping("/update/quantity_aspirant_members") // it should be a put but doesn't work with flutter web
    public ResponseEntity updateAspirantMembersQuantity(Principal principal, int newQuantity) {
        return new ResponseEntity(clubService.updateAspirantMembersQuantity(principal.getName(), newQuantity), HttpStatus.OK);
    }


}
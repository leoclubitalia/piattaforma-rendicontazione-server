package it.leo.rendicontationplatform.controllers;


import it.leo.rendicontationplatform.services.ClubService;
import it.leo.rendicontationplatform.services.RetrieveService;
import it.leo.rendicontationplatform.support.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;


@RestController
@RequestMapping("/club")
public class ClubController {
    @Autowired
    private RetrieveService retrieveService;
    @Autowired
    private ClubService clubService;


    @PreAuthorize("hasAuthority('club')")
    @GetMapping("/details")
    public ResponseEntity getInfoClub() {
        return new ResponseEntity(retrieveService.getInfoClub(Utils.getEmail()), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('club')")
    @GetMapping("/quantity_services_made")
    public ResponseEntity getServicesQuantity() {
        return new ResponseEntity(retrieveService.getQuantityServices(Utils.getEmail()), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('club')")
    @GetMapping("/quantity_activities_made")
    public ResponseEntity getActivitiesQuantity(Principal principal) {
        return new ResponseEntity(retrieveService.getQuantityActivities(Utils.getEmail()), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('club')")
    @GetMapping("/update/quantity_current_members") // it should be a put but doesn't work with flutter web
    public ResponseEntity updateCurrentMembersQuantity(int newQuantity) {
        return new ResponseEntity(clubService.updateCurrentMembersQuantity(Utils.getEmail(), newQuantity), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('club')")
    @GetMapping("/update/quantity_aspirant_members") // it should be a put but doesn't work with flutter web
    public ResponseEntity updateAspirantMembersQuantity(int newQuantity) {
        return new ResponseEntity(clubService.updateAspirantMembersQuantity(Utils.getEmail(), newQuantity), HttpStatus.OK);
    }


}
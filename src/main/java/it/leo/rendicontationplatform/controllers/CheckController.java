package it.leo.rendicontationplatform.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.security.RolesAllowed;
import java.security.Principal;


@RestController
@RequestMapping("/check")
public class CheckController {


    @GetMapping("/simple")
    public ResponseEntity getInfoClub() {
        return new ResponseEntity("Check status ok!", HttpStatus.OK);
    }

    @RolesAllowed("club")
    @GetMapping("/logged")
    public ResponseEntity getServicesQuantity(Principal principal) {
        return new ResponseEntity("Check status ok, hi " + principal.getName() + "!", HttpStatus.OK);
    }


}

package it.leo.rendicontationplatform.controllers;


import it.leo.rendicontationplatform.support.authentication.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/check")
public class CheckController {


    @GetMapping("/simple")
    public ResponseEntity getInfoClub() {
        return new ResponseEntity("Check status ok!", HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('club')")
    @GetMapping("/logged")
    public ResponseEntity getServicesQuantity() {
        return new ResponseEntity("Check status ok, hi " + Utils.getEmail() + "!", HttpStatus.OK);
    }


}

package it.leo.rendicontationplatform.controllers;


import it.leo.rendicontationplatform.services.RetrieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;


@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private RetrieveService retrieveService;


    @GetMapping("/counters/advanced")
    public ResponseEntity getCountersAdvanced(@RequestParam(required = false) Integer clubId,
                                              @RequestParam(required = false) Integer districtId,
                                              @RequestParam(required = false) @DateTimeFormat(pattern = "ddMMyyyy") Date startDate,
                                              @RequestParam(required = false) @DateTimeFormat(pattern = "ddMMyyyy") Date endDate) {
        return new ResponseEntity(retrieveService.getCountersAdvanced(clubId, districtId, startDate, endDate), HttpStatus.OK);
    }


}

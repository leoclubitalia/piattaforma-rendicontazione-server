package it.leo.rendicontationplatform.controllers;


import it.leo.rendicontationplatform.entities.*;
import it.leo.rendicontationplatform.services.RetrieveService;
import it.leo.rendicontationplatform.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Date;


@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private RetrieveService retrieveService;
    @Autowired
    private SearchService searchService;


    @GetMapping("/type/activity/all")
    public ResponseEntity getAllTypeActivity() {
        return new ResponseEntity(retrieveService.getAllTypeActivity(), HttpStatus.OK);
    }

    @GetMapping("/type/service/all")
    public ResponseEntity getAllTypeService() {
        return new ResponseEntity(retrieveService.getAllTypeService(), HttpStatus.OK);
    }

    @GetMapping("/competence_area/all")
    public ResponseEntity getAllCompetencaArea() {
        return new ResponseEntity(retrieveService.getAllCompetenceArea(), HttpStatus.OK);
    }

    @GetMapping("/satisfaction_degree/all")
    public ResponseEntity getAllSatisfactionDegree() {
        return new ResponseEntity(retrieveService.getAllSatisfactionDegree(), HttpStatus.OK);
    }

    @GetMapping("/club/by_district")
    public ResponseEntity getAllClubsByDistrict(@RequestBody @Valid District district) {
        return new ResponseEntity(searchService.findClubsByDistrict(district), HttpStatus.OK);
    }

    @GetMapping("/club/by_name")
    public ResponseEntity getClubByName(String name) {
        return new ResponseEntity(searchService.findClubsByName(name), HttpStatus.OK);
    }

    @GetMapping("/club/by_id")
    public ResponseEntity getClubById(int id) {
        return new ResponseEntity(searchService.findClubsById(id), HttpStatus.OK);
    }

    @GetMapping("/club/by_city")
    public ResponseEntity getClubByCity(@RequestBody @Valid City city) {
        return new ResponseEntity(searchService.findClubsByCity(city), HttpStatus.OK);
    }

    @GetMapping("/club/all/paginated")
    public ResponseEntity getAllClubs(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return new ResponseEntity(retrieveService.getAllClubs(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/club/all")
    public ResponseEntity getAllClubs() {
        return new ResponseEntity(retrieveService.getAllClubs(), HttpStatus.OK);
    }

    @GetMapping("/city/all/paginated")
    public ResponseEntity getAllCities(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return new ResponseEntity(retrieveService.getAllCities(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/city/by_cap")
    public ResponseEntity getCityByCap(@RequestBody @Valid Cap cap) {
        return new ResponseEntity(searchService.findCityByCap(cap), HttpStatus.OK);
    }

    @GetMapping("/city/by_name")
    public ResponseEntity getCityByCap(String name) {
        return new ResponseEntity(searchService.findCityByName(name), HttpStatus.OK);
    }

    @GetMapping("/district/all/paginated")
    public ResponseEntity getAllDistricts(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return new ResponseEntity(retrieveService.getAllDistricts(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/district/all")
    public ResponseEntity getAllDistricts() {
        return new ResponseEntity(retrieveService.getAllDistricts(), HttpStatus.OK);
    }

    @GetMapping("/service/by_club")
    public ResponseEntity getServicesByClub(@RequestBody @Valid Club club, @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return new ResponseEntity(searchService.findServicesByClub(club, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/service/advanced")
    public ResponseEntity getAdvancedSearchServices(@RequestParam(required = false) String title,
                                                    @RequestParam(required = false) @DateTimeFormat(pattern = "ddMMyyyy") Date startDate,
                                                    @RequestParam(required = false) @DateTimeFormat(pattern = "ddMMyyyy") Date endDate,
                                                    @RequestParam(required = false) Integer quantityParticipants,
                                                    @RequestParam(required = false) Integer satisfactionDegree,
                                                    @RequestParam(required = false) Integer duration,
                                                    @RequestParam(required = false) String otherAssociations,
                                                    @RequestParam(required = false) Float minMoneyRaised,
                                                    @RequestParam(required = false) Float maxMoneyRaised,
                                                    @RequestParam(required = false) Integer quantityServedPeople,
                                                    @RequestParam(required = false) Integer cityId,
                                                    @RequestParam(required = false) Integer clubId,
                                                    @RequestParam(required = false) Integer typeServiceId,
                                                    @RequestParam(required = false) Integer competenceAreaId,
                                                    @RequestParam(required = false) Integer districtId,
                                                    Integer pageNumber,
                                                    Integer pageSize) {
        return new ResponseEntity(searchService.findServicesAdvanced(title, startDate, endDate, quantityParticipants, satisfactionDegree, duration, otherAssociations, minMoneyRaised, maxMoneyRaised, quantityServedPeople, cityId, clubId, typeServiceId, competenceAreaId, districtId, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/activity/by_club")
    public ResponseEntity getActivitiesByClub(@RequestBody @Valid Club club, @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return new ResponseEntity(searchService.findActivitiesByClub(club, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/activity/advanced")
    public ResponseEntity getAdvancedSearchActivities(@RequestParam(required = false) String title,
                                                      @RequestParam(required = false) @DateTimeFormat(pattern = "ddMMyyyy") Date startDate,
                                                      @RequestParam(required = false) @DateTimeFormat(pattern = "ddMMyyyy") Date endDate,
                                                      @RequestParam(required = false) Integer quantityLeo,
                                                      @RequestParam(required = false) Integer satisfactionDegree,
                                                      @RequestParam(required = false) Boolean lionsParticipation,
                                                      @RequestParam(required = false) Integer cityId,
                                                      @RequestParam(required = false) Integer clubId,
                                                      @RequestParam(required = false) Integer typeActivityId,
                                                      @RequestParam(required = false) Integer districtId,
                                                      Integer pageNumber,
                                                      Integer pageSize) {
        return new ResponseEntity(searchService.findActivitiesAdvanced(title, startDate, endDate, quantityLeo, satisfactionDegree, lionsParticipation, cityId, clubId, typeActivityId, districtId, pageNumber, pageSize), HttpStatus.OK);
    }


}

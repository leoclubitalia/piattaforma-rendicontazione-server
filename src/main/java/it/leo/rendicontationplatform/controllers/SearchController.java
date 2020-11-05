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


    @GetMapping("/type/activity")
    public ResponseEntity getAllTypeActivity() {
        return new ResponseEntity(retrieveService.getAllTypeActivity(), HttpStatus.OK);
    }

    @GetMapping("/type/service")
    public ResponseEntity getAllTypeService() {
        return new ResponseEntity(retrieveService.getAllTypeService(), HttpStatus.OK);
    }

    @GetMapping("/type/competence_area")
    public ResponseEntity getAllCompetencaArea() {
        return new ResponseEntity(retrieveService.getAllCompetenceArea(), HttpStatus.OK);
    }

    @GetMapping("/club/by_district")
    public ResponseEntity getAllClubsByDistrict(@RequestBody @Valid District district) {
        return new ResponseEntity(searchService.findClubsByDistrict(district), HttpStatus.OK);
    }

    @GetMapping("/club/by_name")
    public ResponseEntity getClubByName(String name) {
        return new ResponseEntity(searchService.findClubsByName(name), HttpStatus.OK);
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

    @GetMapping("/service/by_club")
    public ResponseEntity getServicesByClub(@RequestBody @Valid Club club, @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return new ResponseEntity(searchService.findServicesByClub(club, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/service/advanced")
    public ResponseEntity getAdvancedSearchServices(@RequestParam(value = "title") String title, @RequestParam(value = "startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate, @RequestParam(value = "endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate, @RequestParam(value = "quantityParticipants") int quantityParticipants, @RequestParam(value = "impact") int impact, @RequestParam(value = "duration") int duration, @RequestParam(value = "otherAssociations") String otherAssociations, @RequestParam(value = "minMoneyRaised") float minMoneyRaised, @RequestParam(value = "maxMoneyRaised") float maxMoneyRaised, @RequestParam(value = "quantityServedPeople") int quantityServedPeople, @RequestParam(value = "cityId") @Valid int cityId, @RequestParam(value = "clubId") @Valid int clubId, @RequestParam(value = "typeServiceId") @Valid int typeServiceId, @RequestParam(value = "competenceAreaId") @Valid int competenceAreaId, @RequestParam(value = "districtId") int districtId, @RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "pageSize") int pageSize) {
        return new ResponseEntity(searchService.findServicesAdvanced(title, startDate, endDate, quantityParticipants, impact, duration, otherAssociations, minMoneyRaised, maxMoneyRaised, quantityServedPeople, cityId, clubId, typeServiceId, competenceAreaId, districtId, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/activity/by_club")
    public ResponseEntity getActivitiesByClub(@RequestBody @Valid Club club, @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return new ResponseEntity(searchService.findActivitiesByClub(club, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/activity/advanced")
    public ResponseEntity getAdvancedSearchActivities(@RequestParam(value = "title") String title, @RequestParam(value = "startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate, @RequestParam(value = "endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate, @RequestParam(value = "quantityLeo") int quantityLeo, @RequestParam(value = "satisfactionDegree") int satisfactionDegree, @RequestParam(value = "lionsParticipation") boolean lionsParticipation, @RequestParam(value = "city") @Valid City city, @RequestParam(value = "club") @Valid Club club, @RequestParam(value = "typeActivity") @Valid TypeActivity typeActivity, @RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "pageSize") int pageSize) {
        return new ResponseEntity(searchService.findActivitiesAdvanced(title, startDate, endDate, quantityLeo, satisfactionDegree, lionsParticipation, city, club, typeActivity, pageNumber, pageSize), HttpStatus.OK);
    }


}

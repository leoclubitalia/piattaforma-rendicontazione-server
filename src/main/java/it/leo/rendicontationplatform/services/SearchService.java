package it.leo.rendicontationplatform.services;


import it.leo.rendicontationplatform.entities.*;
import it.leo.rendicontationplatform.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@org.springframework.stereotype.Service
public class SearchService {
    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ActivityRepository activityRepository;


    @Transactional(readOnly = true)
    public List<Club> findClubsByDistrict(District district) {
        return clubRepository.findClubByDistrict(district);
    }

    @Transactional(readOnly = true)
    public List<City> findCityByCap(Cap cap) {
        return cityRepository.findCityByCaps(cap);
    }

    @Transactional(readOnly = true)
    public List<City> findCityByName(String name) {
        return cityRepository.findCityByName(name);
    }

    @Transactional(readOnly = true)
    public List<District> findDistrictByName(String name) {
        return districtRepository.findDistinctByNameContainingIgnoreCase(name);
    }

    @Transactional(readOnly = true)
    public List<Club> findClubsByName(String name) {
        return clubRepository.findClubsByNameContainingIgnoreCase(name);
    }

    @Transactional(readOnly = true)
    public List<Club> findClubsByCity(City city) {
        return clubRepository.findClubByCity(city);
    }

    @Transactional(readOnly = true)
    public List<Service> findServicesByClub(Club club, int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by("date"));
        Page<Service> pagedResult = serviceRepository.findServicesByClub(club, paging);
        if ( pagedResult.hasContent() ) {
            return pagedResult.getContent();
        }
        else {
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Service> findServicesAdvanced(String title, Date startDate, Date endDate, int quantityParticipants, int satisfactionDegree, int impact, int duration, String otherAssociations, float minMoneyRaised, float maxMoneyRaised, int quantityServedPeople, City city, Club club, TypeService typeService, CompetenceArea competenceArea, int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by("date"));
        Page<Service> pagedResult = serviceRepository.findServicesAdvanced(title, startDate, endDate,quantityParticipants, satisfactionDegree, impact, duration, otherAssociations, minMoneyRaised, maxMoneyRaised, quantityServedPeople, city, club, typeService, competenceArea, paging);
        if ( pagedResult.hasContent() ) {
            return pagedResult.getContent();
        }
        else {
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Activity> findActivitiesByClub(Club club, int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by("date"));
        Page<Activity> pagedResult = activityRepository.findActivitiesByClub(club, paging);
        if ( pagedResult.hasContent() ) {
            return pagedResult.getContent();
        }
        else {
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Activity> findActivitiesAdvanced(String title, Date startDate, Date endDate, int quantityLeo, int satisfactionDegree, boolean lionsParticipation, City city, Club club, TypeActivity typeActivity, int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by("date"));
        Page<Activity> pagedResult = activityRepository.findActivitiesAdvanced(title, startDate, endDate, quantityLeo, satisfactionDegree, lionsParticipation, city, club, typeActivity, paging);
        if ( pagedResult.hasContent() ) {
            return pagedResult.getContent();
        }
        else {
            return new ArrayList<>();
        }
    }


}

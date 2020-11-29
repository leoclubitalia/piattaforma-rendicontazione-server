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
import java.util.Calendar;
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
    @Autowired
    private ResearchServiceRepository researchServiceRepository;
    @Autowired
    private ResearchActivityRepository researchActivityRepository;


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
        return cityRepository.findCityByNameContainingIgnoreCase(name);
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
    public Club findClubsById(int id) {
        return clubRepository.findClubById(id);
    }

    @Transactional(readOnly = true)
    public List<Club> findClubsByCity(City city) {
        return clubRepository.findClubByCity(city);
    }

    @Transactional(readOnly = true)
    public List<Service> findServicesByClub(Club club, int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by("date").descending());
        Page<Service> pagedResult = serviceRepository.findServicesByClub(club, paging);
        if ( pagedResult.hasContent() ) {
            return pagedResult.getContent();
        }
        else {
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = false)
    public List<Service> findServicesAdvanced(String title, Date startDate, Date endDate, Integer quantityParticipants, Integer satisfactionDegree, Integer duration, String otherAssociations, String moneyOrMaterialCollected, Integer quantityServedPeople, Integer cityId, Integer clubId, Integer typeServiceId, Integer competenceAreaId, Integer districtId, Integer pageNumber, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by("date").descending());
        if ( endDate != null ) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endDate);
            calendar.add(Calendar.HOUR, 23);
            endDate = calendar.getTime();
        }
        Page<Service> pagedResult = serviceRepository.findServicesAdvanced(title, startDate, endDate, quantityParticipants, satisfactionDegree, duration, otherAssociations, moneyOrMaterialCollected, quantityServedPeople, cityId, clubId, typeServiceId, competenceAreaId, districtId, paging);
        ResearchService research = new ResearchService();
        research.setTitle(title);
        research.setStartDate(startDate);
        research.setEndDate(endDate);
        research.setQuantityParticipants(quantityParticipants);
        research.setDuration(duration);
        research.setOtherAssociations(otherAssociations);
        research.setMoneyOrMaterialCollected(moneyOrMaterialCollected);
        research.setQuantityServedPeople(quantityServedPeople);
        if ( satisfactionDegree != null ) {
            research.setSatisfactionDegree(new SatisfactionDegree(satisfactionDegree));
        }
        if ( cityId != null ) {
            research.setCity(new City(cityId));
        }
        if ( clubId != null ) {
            research.setClub(new Club(clubId));
        }
        if ( typeServiceId != null ) {
            research.setTypeService(new TypeService(typeServiceId));
        }
        if ( competenceAreaId != null ) {
            research.setCompetenceArea(new CompetenceArea(competenceAreaId));
        }
        if ( districtId != null ) {
            research.setDistrict(new District(districtId));
        }
        researchServiceRepository.save(research);
        if ( pagedResult.hasContent() ) {
            return pagedResult.getContent();
        }
        else {
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Activity> findActivitiesByClub(Club club, int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by("date").descending());
        Page<Activity> pagedResult = activityRepository.findActivitiesByClub(club, paging);
        if ( pagedResult.hasContent() ) {
            return pagedResult.getContent();
        }
        else {
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = false)
    public List<Activity> findActivitiesAdvanced(String title, Date startDate, Date endDate, Integer quantityLeo, Integer satisfactionDegree, Boolean lionsParticipation, Integer cityId, Integer clubId, Integer typeActivityId, Integer districtId, Integer pageNumber, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by("date").descending());
        if ( endDate != null ) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endDate);
            calendar.add(Calendar.HOUR, 23);
            endDate = calendar.getTime();
        }
        Page<Activity> pagedResult = activityRepository.findActivitiesAdvanced(title, startDate, endDate, quantityLeo, satisfactionDegree, lionsParticipation, cityId, clubId, districtId, typeActivityId, paging);
        ResearchActivity research = new ResearchActivity();
        research.setTitle(title);
        research.setStartDate(startDate);
        research.setEndDate(endDate);
        research.setQuantityLeo(quantityLeo);
        research.setLionsParticipation(lionsParticipation);
        if ( satisfactionDegree != null ) {
            research.setSatisfactionDegree(new SatisfactionDegree(satisfactionDegree));
        }
        if ( cityId != null ) {
            research.setCity(new City(cityId));
        }
        if ( clubId != null ) {
            research.setClub(new Club(clubId));
        }
        if ( typeActivityId != null ) {
            research.setTypeActivity(new TypeActivity(typeActivityId));
        }
        if ( districtId != null ) {
            research.setDistrict(new District(districtId));
        }
        researchActivityRepository.save(research);
        if ( pagedResult.hasContent() ) {
            return pagedResult.getContent();
        }
        else {
            return new ArrayList<>();
        }
    }


}

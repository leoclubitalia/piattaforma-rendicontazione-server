package it.leo.rendicontationplatform.services;


import it.leo.rendicontationplatform.entities.*;
import it.leo.rendicontationplatform.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;


@Service
public class RetrieveService {
    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private TypeActivityRepository typeActivityRepository;
    @Autowired
    private TypeServiceRepository typeServiceRepository;
    @Autowired
    private CompetenceAreaRepository competenceAreaRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private DistrictRepository districtRepository;


    @Transactional(readOnly = true)
    public Club getInfoClub(String name) {
        return clubRepository.findClubByName(name);
    }

    @Transactional(readOnly = true)
    public Map<String, Integer> getQuantityServices(int id) {
        Map<String, Integer> result = new HashMap<>();
        result.put("all", serviceRepository.countServicesByClubId(id));
        result.put("current_year", serviceRepository.countAllServicesByClubIdAndSocialYear(id, getStartDateCurrentSocialYear()));
        return result;
    }

    @Transactional(readOnly = true)
    public Map<String, Integer> getQuantityActivities(int id) {
        Map<String, Integer> result = new HashMap<>();
        result.put("all", activityRepository.countActivitiesByClubId(id));
        result.put("current_year", activityRepository.countAllActivitiesByClubIdAndSocialYear(id, getStartDateCurrentSocialYear()));
        return result;
    }

    @Transactional(readOnly = true)
    public List<TypeActivity> getAllTypeActivity() {
        return typeActivityRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<TypeService> getAllTypeService() {
        return typeServiceRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<CompetenceArea> getAllCompetenceArea() {
        return competenceAreaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Club> getAllClubs(int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        Page<Club> pagedResult = clubRepository.findAll(paging);
        if ( pagedResult.hasContent() ) {
            return pagedResult.getContent();
        }
        else {
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<City> getAllCities(int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        Page<City> pagedResult = cityRepository.findAll(paging);
        if ( pagedResult.hasContent() ) {
            return pagedResult.getContent();
        }
        else {
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<District> getAllDistricts(int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        Page<District> pagedResult = districtRepository.findAll(paging);
        if ( pagedResult.hasContent() ) {
            return pagedResult.getContent();
        }
        else {
            return new ArrayList<>();
        }
    }

    private Date getStartDateCurrentSocialYear() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        if (calendar.get(Calendar.MONTH) < 7 ) {
            calendar.set(calendar.get(calendar.YEAR) - 1, 7, 1);
        }
        else {
            calendar.set(calendar.get(calendar.YEAR), 7, 1);
        }
        return calendar.getTime();
    }


}
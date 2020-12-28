package it.leo.rendicontationplatform.services;


import it.leo.rendicontationplatform.entities.*;
import it.leo.rendicontationplatform.repositories.*;
import it.leo.rendicontationplatform.support.objects.Report;
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
    @Autowired
    private SatisfactionDegreeRepository satisfactionDegreeRepository;


    @Transactional(readOnly = true)
    public Club getInfoClub(String email) {
        return clubRepository.findClubByEmail(email);
    }

    @Transactional(readOnly = true)
    public Map<String, Integer> getQuantityServices(String email) {
        Map<String, Integer> result = new HashMap();
        int id = clubRepository.findClubByEmail(email).getId();
        result.put("all", serviceRepository.countServicesByClubIdAndDeletedFalse(id));
        result.put("current_year", serviceRepository.countAllServicesByClubIdAndSocialYear(id, getStartDateCurrentSocialYear()));
        return result;
    }

    @Transactional(readOnly = true)
    public Map<String, Integer> getQuantityActivities(String email) {
        Map<String, Integer> result = new HashMap();
        int id = clubRepository.findClubByEmail(email).getId();
        result.put("all", activityRepository.countActivitiesByClubIdAndDeletedFalse(id));
        result.put("current_year", activityRepository.countAllActivitiesByClubIdAndSocialYear(id, getStartDateCurrentSocialYear()));
        return result;
    }

    public Map<String, Object> getCountersAdvanced(Integer clubId, Integer districtId, Date startDate, Date endDate) {
        Map<String, Object> result = new HashMap();
        result.put("services", serviceRepository.countAllServicesAdvanced(clubId, districtId, null, startDate, endDate));
        result.put("activities", activityRepository.countAllActivitiesAdvanced(clubId, districtId, null, startDate, endDate));
        List<Report> serviceAreaReport = new LinkedList();
        List<CompetenceArea> areas = getAllCompetenceArea();
        for ( CompetenceArea area : areas ) {
            serviceAreaReport.add(new Report(area.getName(), serviceRepository.countAllServicesAdvanced(clubId, districtId, area.getId(), startDate, endDate)));
        }
        result.put("serviceAreaReport", serviceAreaReport);

        List<Report> activityTypeReport = new LinkedList();
        List<TypeActivity> typeActivities = getAllTypeActivity();
        for ( TypeActivity type : typeActivities ) {
            activityTypeReport.add(new Report(type.getName(), serviceRepository.countAllServicesAdvanced(clubId, districtId, type.getId(), startDate, endDate)));
        }
        result.put("activityTypeReport", activityTypeReport);
        if ( clubId != null ) {
            Club club = clubRepository.findClubById(clubId);
            result.put("members", club.getCurrentMembers());
            result.put("aspirants", club.getAspirantMembers());
        }
        else {
            result.put("members", districtRepository.countMembersAdvanced(districtId));
            result.put("aspirants", districtRepository.countAspirantsAdvanced(districtId));
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<TypeActivity> getAllTypeActivity() {
        return typeActivityRepository.findAllTypeActivityEnabled();
    }

    @Transactional(readOnly = true)
    public List<SatisfactionDegree> getAllSatisfactionDegree() {
        return satisfactionDegreeRepository.findAllSatisfactionDegreesEnabled();
    }

    @Transactional(readOnly = true)
    public List<TypeService> getAllTypeService() {
        return typeServiceRepository.findAllTypeServiceEnabled();
    }

    @Transactional(readOnly = true)
    public List<CompetenceArea> getAllCompetenceArea() {
        return competenceAreaRepository.findAllCompetenceAreasEnabled();
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
            return new ArrayList();
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
            return new ArrayList();
        }
    }

    @Transactional(readOnly = true)
    public List<District> getAllDistricts() {
        return districtRepository.findAllDistrictsEnabled();
    }

    @Transactional(readOnly = true)
    public List<District> getAllDistricts(int pageNumber, int pageSize) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        Page<District> pagedResult = districtRepository.findAll(paging);
        if ( pagedResult.hasContent() ) {
            return pagedResult.getContent();
        }
        else {
            return new ArrayList();
        }
    }

    private Date getStartDateCurrentSocialYear() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        if (calendar.get(Calendar.MONTH) < 7 ) {
            calendar.set(calendar.get(calendar.YEAR) - 1, 6, 1);
        }
        else {
            calendar.set(calendar.get(calendar.YEAR), 6, 1);
        }
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.SECOND, calendar.get(calendar.SECOND) - 1);
        return calendar.getTime();
    }


}
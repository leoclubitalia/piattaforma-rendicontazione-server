package it.leo.rendicontationplatform.services;


import it.leo.rendicontationplatform.entities.Club;
import it.leo.rendicontationplatform.repositories.ActivityRepository;
import it.leo.rendicontationplatform.repositories.ClubRepository;
import it.leo.rendicontationplatform.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class RetrieveService {
    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ActivityRepository activityRepository;


    @Transactional(readOnly = true)
    public Club getInfoClub(String name) {
        return clubRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Map<String, Integer> getQuantityServices(Club club) {
        Map<String, Integer> result = new HashMap<>();
        result.put("all", serviceRepository.countAllByClub(club));
        result.put("current_year", serviceRepository.countAllByClubAndSocialYear(club, getStartDateCurrentSocialYear()));
        return result;
    }

    @Transactional(readOnly = true)
    public Map<String, Integer> getQuantityActivities(Club club) {
        Map<String, Integer> result = new HashMap<>();
        result.put("all", activityRepository.countAllByClub(club));
        result.put("current_year", activityRepository.countAllByClubAndSocialYear(club, getStartDateCurrentSocialYear()));
        return result;
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
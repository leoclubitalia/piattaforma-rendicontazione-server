package it.leo.rendicontationplatform.services;


import it.leo.rendicontationplatform.entities.Activity;
import it.leo.rendicontationplatform.entities.TypeActivity;
import it.leo.rendicontationplatform.repositories.ActivityRepository;
import it.leo.rendicontationplatform.repositories.ClubRepository;
import it.leo.rendicontationplatform.repositories.TypeActivityRepository;
import it.leo.rendicontationplatform.support.exceptions.ActivityAlreadyExistException;
import it.leo.rendicontationplatform.support.exceptions.UnableToAddActivityForSomeoneElseException;
import it.leo.rendicontationplatform.support.exceptions.UnableToEditActivityForSomeoneElseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;


@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private TypeActivityRepository typeActivityRepository;
    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private EntityManager entityManager;


    @Transactional(readOnly = false)
    public Activity addActivity(String email, Activity activity) throws ActivityAlreadyExistException, UnableToAddActivityForSomeoneElseException {
        if ( activity.getClub().getId() != clubRepository.findClubByEmail(email).getId() ) {
            throw new UnableToAddActivityForSomeoneElseException();
        }
        if ( activityRepository.existsActivityByTitleAndDateAndClub(activity.getTitle(), activity.getDate(), activity.getClub()) ) {
            throw new ActivityAlreadyExistException();
        }
        activity = activityRepository.saveAndFlush(activity);
        entityManager.refresh(activity);
        return activity;
    }

    @Transactional(readOnly = false)
    public Activity editActivity(String email, Activity activity) throws UnableToEditActivityForSomeoneElseException {
        if ( activity.getClub().getId() != clubRepository.findClubByEmail(email).getId() ) {
            throw new UnableToEditActivityForSomeoneElseException();
        }
        Activity attached = activityRepository.findActivityById(activity.getId());
        attached.setTitle(activity.getTitle());
        attached.setDate(activity.getDate());
        attached.setDescription(activity.getDescription());
        attached.setQuantityLeo(activity.getQuantityLeo());
        attached.setSatisfactionDegree(activity.getSatisfactionDegree());
        Set<TypeActivity> types = new HashSet();
        for (TypeActivity type : activity.getTypesActivity()) {
            types.add(typeActivityRepository.findTypeActivityById(type.getId()));
        }
        attached.setTypesActivity(types);
        attached.setLionsParticipation(activity.isLionsParticipation());
        attached.setCity(activity.getCity());
        return activityRepository.save(attached);
    }


}

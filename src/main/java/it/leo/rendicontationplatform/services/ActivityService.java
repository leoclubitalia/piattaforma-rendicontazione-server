package it.leo.rendicontationplatform.services;


import it.leo.rendicontationplatform.entities.Activity;
import it.leo.rendicontationplatform.entities.TypeActivity;
import it.leo.rendicontationplatform.repositories.ActivityRepository;
import it.leo.rendicontationplatform.repositories.ClubRepository;
import it.leo.rendicontationplatform.repositories.TypeActivityRepository;
import it.leo.rendicontationplatform.support.exceptions.*;
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
    public Activity addActivity(String email, Activity activity) throws UnableToAddActivityForSomeoneElseException {
        if ( activity.getClub().getId() != clubRepository.findClubByEmail(email).getId() ) {
            throw new UnableToAddActivityForSomeoneElseException();
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
        return attached;
    }

    @Transactional(readOnly = false)
    public Activity deleteActivity(String email, Integer activityId) throws UnableToDeleteActivityForSomeoneElseException {
        Activity attached = activityRepository.findActivityById(activityId);
        if ( attached.getClub().getId() != clubRepository.findClubByEmail(email).getId() ) {
            throw new UnableToDeleteActivityForSomeoneElseException();
        }
        attached.setDeleted(true);
        return attached;
    }


}

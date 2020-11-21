package it.leo.rendicontationplatform.services;


import it.leo.rendicontationplatform.entities.Activity;
import it.leo.rendicontationplatform.repositories.ActivityRepository;
import it.leo.rendicontationplatform.support.exceptions.ActivityAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;


@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private EntityManager entityManager;


    @Transactional(readOnly = false)
    public Activity addActivity(Activity activity) throws ActivityAlreadyExistException {
        if ( activityRepository.existsActivityByTitleAndDateAndClub(activity.getTitle(), activity.getDate(), activity.getClub()) ) {
            throw new ActivityAlreadyExistException();
        }
        activity = activityRepository.saveAndFlush(activity);
        entityManager.refresh(activity);
        return activity;
    }

    @Transactional(readOnly = false)
    public Activity editActivity(Activity activity) {
        return activityRepository.save(activity);
    }


}

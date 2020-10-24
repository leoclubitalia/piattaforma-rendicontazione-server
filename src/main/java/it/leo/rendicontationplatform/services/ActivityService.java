package it.leo.rendicontationplatform.services;


import it.leo.rendicontationplatform.entities.Activity;
import it.leo.rendicontationplatform.repositories.ActivityRepository;
import it.leo.rendicontationplatform.support.exceptions.ActivityAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;


    @Transactional(readOnly = false)
    public Activity addActivity(Activity activity) throws ActivityAlreadyExistException {
        if ( activityRepository.existsActivityByTitleAndDateAndClub() ) {
            throw new ActivityAlreadyExistException();
        }
        return activityRepository.save(activity);
    }

    @Transactional(readOnly = false)
    public Activity editActivity(Activity activity) {
        return activityRepository.save(activity);
    }


}

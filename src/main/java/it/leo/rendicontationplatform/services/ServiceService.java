package it.leo.rendicontationplatform.services;


import it.leo.rendicontationplatform.entities.Service;
import it.leo.rendicontationplatform.repositories.ClubRepository;
import it.leo.rendicontationplatform.repositories.ServiceRepository;
import it.leo.rendicontationplatform.support.exceptions.ServiceAlreadyExistException;
import it.leo.rendicontationplatform.support.exceptions.UnableToAddServiceForSomeoneElseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;


@org.springframework.stereotype.Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private EntityManager entityManager;


    @Transactional(readOnly = false)
    public Service addService(String email, Service service) throws ServiceAlreadyExistException, UnableToAddServiceForSomeoneElseException {
        if ( service.getClub().getId() != clubRepository.findClubByEmail(email).getId() ) {
            throw new UnableToAddServiceForSomeoneElseException();
        }
        if ( serviceRepository.existsServiceByTitleAndDateAndClub(service.getTitle(), service.getDate(), service.getClub()) ) {
            throw new ServiceAlreadyExistException();
        }
        service = serviceRepository.saveAndFlush(service);
        entityManager.refresh(service);
        return service;
    }

    @Transactional(readOnly = false)
    public Service editService(Service service) {
        return serviceRepository.save(service);
    }


}

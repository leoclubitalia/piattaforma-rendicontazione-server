package it.leo.rendicontationplatform.services;


import it.leo.rendicontationplatform.entities.Service;
import it.leo.rendicontationplatform.repositories.ServiceRepository;
import it.leo.rendicontationplatform.support.exceptions.ServiceAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@org.springframework.stereotype.Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;


    @Transactional(readOnly = false)
    public Service addService(Service service) throws ServiceAlreadyExistException {
        if ( serviceRepository.existsServiceByTitleAndDateAndClub(service.getTitle(), service.getDate(), service.getClub()) ) {
            throw new ServiceAlreadyExistException();
        }
        return serviceRepository.save(service);
    }

    @Transactional(readOnly = false)
    public Service editService(Service service) {
        return serviceRepository.save(service);
    }


}

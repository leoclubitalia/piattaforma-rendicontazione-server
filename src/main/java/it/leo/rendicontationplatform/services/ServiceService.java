package it.leo.rendicontationplatform.services;


import it.leo.rendicontationplatform.entities.*;
import it.leo.rendicontationplatform.repositories.ClubRepository;
import it.leo.rendicontationplatform.repositories.CompetenceAreaRepository;
import it.leo.rendicontationplatform.repositories.ServiceRepository;
import it.leo.rendicontationplatform.repositories.TypeServiceRepository;
import it.leo.rendicontationplatform.support.exceptions.ServiceAlreadyExistException;
import it.leo.rendicontationplatform.support.exceptions.UnableToAddServiceForSomeoneElseException;
import it.leo.rendicontationplatform.support.exceptions.UnableToEditServiceForSomeoneElseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;


@org.springframework.stereotype.Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private TypeServiceRepository typeServiceRepository;
    @Autowired
    private CompetenceAreaRepository competenceAreaRepository;
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
    public Service editService(String email, Service service) throws UnableToEditServiceForSomeoneElseException, ServiceAlreadyExistException {
        if ( service.getClub().getId() != clubRepository.findClubByEmail(email).getId() ) {
            throw new UnableToEditServiceForSomeoneElseException();
        }
        if ( serviceRepository.existsServiceByTitleAndDateAndClubAndIdIsNot(service.getTitle(), service.getDate(), service.getClub(), service.getId()) ) {
            throw new ServiceAlreadyExistException();
        }
        Service attached = serviceRepository.findServiceById(service.getId());
        attached.setTitle(service.getTitle());
        attached.setDate(service.getDate());
        attached.setDescription(service.getDescription());
        attached.setCity(service.getCity());
        attached.setSatisfactionDegree(service.getSatisfactionDegree());
        attached.setQuantityParticipants(service.getQuantityParticipants());
        attached.setQuantityServedPeople(service.getQuantityServedPeople());
        attached.setDuration(service.getDuration());
        attached.setMoneyOrMaterialCollected(service.getMoneyOrMaterialCollected());
        Set<TypeService> types = new HashSet();
        for (TypeService type : service.getTypesService()) {
            types.add(typeServiceRepository.findTypeServiceById(type.getId()));
        }
        attached.setTypesService(types);
        Set<CompetenceArea> areas = new HashSet();
        for (CompetenceArea area : service.getCompetenceAreasService()) {
            areas.add(competenceAreaRepository.findCompetenceAreaById(area.getId()));
        }
        attached.setCompetenceAreasService(areas);
        attached.setOtherAssociations(service.getOtherAssociations());
        return serviceRepository.save(attached);
    }


}

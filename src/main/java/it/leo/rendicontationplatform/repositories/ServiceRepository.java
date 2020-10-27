package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.Date;


@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
    boolean existsServiceByTitleAndDateAndClub(String title, Date date, Club club);
    int countServicesByClub(Club club);
    Page<Service> findServicesByClub(Club club, Pageable pageable);

    @Query("SELECT COUNT(s) " +
           "FROM Service s " +
           "WHERE s.club = ?1 AND " +
           "s.date > ?2")
    int countAllServicesByClubAndSocialYear(Club club, Date startDate);

    @Query("SELECT s " +
           "FROM Service s " +
           "WHERE (s.title LIKE ?1 OR ?1 IS NULL) AND " +
           "      ((s.date >= ?2 AND s.date <= ?3) OR (s.date >= ?2 AND ?3 IS NULL) OR (s.date <= ?3 AND ?2 IS NULL) OR (?2 IS NULL AND ?3 IS NULL)) AND " +
           "      (s.quantityParticipants >= ?4 OR ?4 IS NULL) AND " +
           "      (s.satisfactionDegree = ?5 OR ?5 IS NULL) AND " +
           "      (s.impact = ?6 OR ?6 IS NULL) AND " +
           "      (s.duration = ?7 OR ?7 IS NULL) AND " +
           "      (s.otherAssociations LIKE ?8 OR ?8 IS NULL) AND " +
           "      ((s.moneyRaised >= ?9 AND s.moneyRaised <= ?10) OR (s.moneyRaised >= ?9 AND ?10 IS NULL) OR (s.moneyRaised <= ?10 AND ?9 IS NULL) OR (?9 IS NULL AND ?10 IS NULL)) AND " +
           "      (s.quantityServedPeople >= ?11 OR ?11 IS NULL) AND " +
           "      (s.city = ?12 OR ?12 IS NULL) AND " +
           "      (s.club = ?13 OR ?13 IS NULL) AND " +
           "      (?14 IN (s.typesService) OR ?14 IS NULL) AND " +
           "      (?15 IN (s.competenceAreasService) OR ?15 IS NULL) " )
    Page<Service> findServicesAdvanced(String title, Date startDate, Date endDate, int quantityParticipants, int satisfactionDegree, int impact, int duration, String otherAssociations, float minMoneyRaised, float maxMoneyRaised, int quantityServedPeople, City city, Club club, TypeService typeService, CompetenceArea competenceArea, Pageable pageable);
}
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
    boolean existsServiceByTitleAndDateAndClubAndDeletedFalse(String title, Date date, Club club);
    boolean existsServiceByTitleAndDateAndClubAndIdIsNotAndDeletedFalse(String title, Date date, Club club, int id);
    Service findServiceById(int id);

    @Query("SELECT COUNT(DISTINCT s) " +
            "FROM Service s LEFT JOIN s.competenceAreasService c " +
            "WHERE (s.club.id = ?1 OR ?1 IS NULL) AND " +
            "      (s.club.district.id = ?2 OR ?2 IS NULL) AND " +
            "      (?3 = c.id OR ?3 IS NULL) AND " +
            "      ((s.date >= ?4 AND s.date <= ?5) OR (s.date >= ?4 AND ?5 IS NULL) OR (s.date <= ?5 AND ?4 IS NULL) OR (?4 IS NULL AND ?5 IS NULL)) AND " +
            "      s.deleted = FALSE")
    int countServicesAdvanced(Integer clubId, Integer districtId, Integer areaId, Date startDate, Date endDate);

    @Query("SELECT s " +
           "FROM Service s LEFT JOIN s.typesService t LEFT JOIN s.competenceAreasService c " +
           "WHERE (s.title LIKE ?1 OR ?1 IS NULL) AND " +
           "      ((s.date >= ?2 AND s.date <= ?3) OR (s.date >= ?2 AND ?3 IS NULL) OR (s.date <= ?3 AND ?2 IS NULL) OR (?2 IS NULL AND ?3 IS NULL)) AND " +
           "      (s.quantityParticipants <= ?4 OR ?4 IS NULL) AND " +
           "      (s.satisfactionDegree.id = ?5 OR ?5 IS NULL) AND " +
           "      (s.duration = ?6 OR ?6 IS NULL) AND " +
           "      (s.otherAssociations LIKE ?7 OR ?7 IS NULL) AND " +
           "      (s.moneyOrMaterialCollected LIKE ?8 OR ?8 IS NULL) AND " +
           "      (s.quantityServedPeople >= ?9 OR ?9 IS NULL) AND " +
           "      (s.city.id = ?10 OR ?10 IS NULL) AND " +
           "      (s.club.id = ?11 OR ?11 IS NULL) AND " +
           "      (s.club.district.id = ?14 OR ?14 IS NULL) AND " +
           "      (?12 = t.id OR ?12 IS NULL) AND " +
           "      (?13 = c.id OR ?13 IS NULL) AND " +
           "      s.deleted = FALSE " +
           "GROUP BY s")
    Page<Service> findServicesAdvanced(String title, Date startDate, Date endDate, Integer quantityParticipants, Integer satisfactionDegree, Integer duration, String otherAssociations, String moneyOrMaterialCollected, Integer quantityServedPeople, Integer cityId, Integer club, Integer typeServiceId, Integer competenceAreaId, Integer districtId, Pageable pageable);
}
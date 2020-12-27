package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    boolean existsActivityByTitleAndDateAndClub(String title, Date date, Club club);
    boolean existsActivityByTitleAndDateAndClubAndIdIsNot(String title, Date date, Club club, int id);
    int countActivitiesByClubId(int clubId);
    Page<Activity> findActivitiesByClub(Club club, Pageable pageable);
    Activity findActivityById(int id);

    @Query("SELECT COUNT(a) " +
           "FROM Activity a " +
           "WHERE a.club.id = ?1 AND " +
           "a.date > ?2")
    int countAllActivitiesByClubIdAndSocialYear(Integer clubId, Date startDate);

    @Query("SELECT COUNT(a) " +
            "FROM Activity a " +
            "WHERE (a.club.id = ?1 OR ?1 IS NULL) AND " +
            "      (a.club.district.id = ?2 OR ?2 IS NULL) AND " +
            "      ((a.date >= ?3 AND a.date <= ?4) OR (a.date >= ?3 AND ?4 IS NULL) OR (a.date <= ?4 AND ?3 IS NULL) OR (?3 IS NULL AND ?4 IS NULL)) ")
    int countAllActivitiesAdvanced(Integer clubId, Integer districtId, Date startDate, Date endDate);

    @Query("SELECT a " +
           "FROM Activity a LEFT JOIN a.typesActivity t " +
           "WHERE (a.title LIKE ?1 OR ?1 IS NULL) AND " +
           "      ((a.date >= ?2 AND a.date <= ?3) OR (a.date >= ?2 AND ?3 IS NULL) OR (a.date <= ?3 AND ?2 IS NULL) OR (?2 IS NULL AND ?3 IS NULL)) AND " +
           "      (a.quantityLeo <= ?4 OR ?4 IS NULL) AND " +
           "      (a.satisfactionDegree.id = ?5 OR ?5 IS NULL) AND " +
           "      (a.lionsParticipation = ?6 OR ?6 IS NULL) AND " +
           "      (a.city.id = ?7 OR ?7 IS NULL) AND " +
           "      (a.club.id = ?8 OR ?8 IS NULL) AND " +
           "      (a.club.district.id = ?9 OR ?9 IS NULL) AND " +
           "      (?10 = t.id OR ?10 IS NULL) " +
           "GROUP BY a ")
    Page<Activity> findActivitiesAdvanced(String title, Date startDate, Date endDate, Integer quantityLeo, Integer satisfactionDegree, Boolean lionsParticipation, Integer cityId, Integer clubId, Integer districtId, Integer typeActivityId, Pageable pageable);


}
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
    int countActivitiesByClubId(int clubId);
    Page<Activity> findActivitiesByClub(Club club, Pageable pageable);

    @Query("SELECT COUNT(a) " +
           "FROM Activity a " +
           "WHERE a.club.id = ?1 AND " +
           "a.date > ?2")
    int countAllActivitiesByClubIdAndSocialYear(int clubId, Date startDate);

    @Query("SELECT a " +
           "FROM Activity a " +
           "WHERE (a.title LIKE ?1 OR ?1 IS NULL) AND " +
           "      ((a.date >= ?2 AND a.date <= ?3) OR (a.date >= ?2 AND ?3 IS NULL) OR (a.date <= ?3 AND ?2 IS NULL) OR (?2 IS NULL AND ?3 IS NULL)) AND " +
           "      (a.quantityLeo >= ?4 OR ?4 IS NULL) AND " +
           "      (a.satisfactionDegree = ?5 OR ?5 IS NULL) AND " +
           "      (a.lionsParticipation = ?6 OR ?6 IS NULL) AND " +
           "      (a.city = ?7 OR ?7 IS NULL) AND " +
           "      (a.club = ?8 OR ?8 IS NULL) AND " +
           "      (?9 IN (a.typesActivity) OR ?9 IS NULL) " )
    Page<Activity> findActivitiesAdvanced(String title, Date startDate, Date endDate, int quantityLeo, int satisfactionDegree, boolean lionsParticipation, City city, Club club, TypeActivity typeActivity, Pageable pageable);
}
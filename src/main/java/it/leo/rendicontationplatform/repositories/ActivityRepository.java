package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.Activity;
import it.leo.rendicontationplatform.entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    boolean existsActivityByTitleAndDateAndClub(String title, Date date, Club club);
    int countAllByClub(Club club);
    @Query("SELECT COUNT(s) " +
           "FROM Service s " +
           "WHERE s.club = ?1 AND " +
           "s.date > ?2")
    int countAllByClubAndSocialYear(Club club, Date startDate);
}
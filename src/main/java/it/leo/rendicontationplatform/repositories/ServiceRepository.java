package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.Club;
import it.leo.rendicontationplatform.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
    boolean existsActivityByTitleAndDateAndClub(String title, Date date, Club club);
    int countAllByClub(Club club);
    @Query("SELECT COUNT(a) " +
            "FROM Activity a " +
            "WHERE a.club = ?1 AND " +
            "a.date > ?2")
    int countAllByClubAndSocialYear(Club club, Date startDate);
}
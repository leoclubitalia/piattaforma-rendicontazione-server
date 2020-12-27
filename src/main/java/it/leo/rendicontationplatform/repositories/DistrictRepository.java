package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.CompetenceArea;
import it.leo.rendicontationplatform.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    List<District> findDistinctByNameContainingIgnoreCase(String name);

    @Query("SELECT SUM(c.currentMembers) " +
            "FROM Club c " +
            "WHERE c.district.id = ?1 OR ?1 IS NULL AND " +
            "      c.enabled = true ")
    int countMembersAdvanced(Integer districtId);

    @Query("SELECT SUM(c.aspirantMembers) " +
            "FROM Club c " +
            "WHERE c.district.id = ?1 OR ?1 IS NULL AND " +
            "      c.enabled = true ")
    int countAspirantsAdvanced(Integer districtId);

    @Query("SELECT d FROM District d WHERE d.enabled = true")
    List<District> findAllDistrictsEnabled();


}

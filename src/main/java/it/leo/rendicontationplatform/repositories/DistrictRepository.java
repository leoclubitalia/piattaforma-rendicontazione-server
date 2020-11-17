package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.CompetenceArea;
import it.leo.rendicontationplatform.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    List<District> findDistinctByNameContainingIgnoreCase(String name);
    @Query("SELECT d FROM District d WHERE d.enabled = true")
    List<District> findAllDistrictsEnabled();
}

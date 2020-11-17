package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.CompetenceArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CompetenceAreaRepository extends JpaRepository<CompetenceArea, Integer> {
    CompetenceArea findCompetenceAreaById(int id);
    @Query("SELECT c FROM CompetenceArea c WHERE c.enabled = true")
    List<CompetenceArea> findAllCompetenceAreasEnabled();
}

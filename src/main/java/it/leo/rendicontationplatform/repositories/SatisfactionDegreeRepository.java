package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.SatisfactionDegree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface SatisfactionDegreeRepository extends JpaRepository<SatisfactionDegree, Integer> {
    @Query("SELECT s FROM SatisfactionDegree s WHERE s.enabled = true")
    List<SatisfactionDegree> findAllSatisfactionDegreesEnabled();
}

package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.CompetenceArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompetenceAreaRepository extends JpaRepository<CompetenceArea, Integer> {
    CompetenceArea findCompetenceAreaById(int id);
}

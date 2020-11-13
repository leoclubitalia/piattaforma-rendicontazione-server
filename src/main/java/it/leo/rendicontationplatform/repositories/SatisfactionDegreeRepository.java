package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.SatisfactionDegree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SatisfactionDegreeRepository extends JpaRepository<SatisfactionDegree, Integer> {
}

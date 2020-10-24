package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.TypeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TypeServiceRepository extends JpaRepository<TypeService, Integer> {
}

package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.TypeActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TypeActivityRepository extends JpaRepository<TypeActivity, Integer> {
}

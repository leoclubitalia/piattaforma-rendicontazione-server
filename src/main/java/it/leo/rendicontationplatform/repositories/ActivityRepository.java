package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
}

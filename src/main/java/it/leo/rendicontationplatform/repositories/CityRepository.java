package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}

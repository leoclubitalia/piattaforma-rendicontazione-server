package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.Cap;
import it.leo.rendicontationplatform.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    List<City> findCityByCaps(Cap caps);
    List<City> findCityByName(String name);
}

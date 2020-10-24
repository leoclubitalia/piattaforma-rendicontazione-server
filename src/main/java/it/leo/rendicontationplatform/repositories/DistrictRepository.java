package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
}

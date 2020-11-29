package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.City;
import it.leo.rendicontationplatform.entities.Club;
import it.leo.rendicontationplatform.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ClubRepository extends JpaRepository<Club, Integer> {
    Club findClubByName(String name);
    Club findClubByEmail(String email);
    Club findClubById(int id);
    List<Club> findClubByDistrict(District district);
    List<Club> findClubsByNameContainingIgnoreCase(String name);
    List<Club> findClubByCity(City city);
}

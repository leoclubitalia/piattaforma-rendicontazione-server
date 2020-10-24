package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClubRepository extends JpaRepository<Club, Integer> {
    Club findByName(String name);
}

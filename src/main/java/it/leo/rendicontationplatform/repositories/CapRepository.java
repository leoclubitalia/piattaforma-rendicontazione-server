package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.Cap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CapRepository extends JpaRepository<Cap, Integer> {
}


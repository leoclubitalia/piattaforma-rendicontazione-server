package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
}

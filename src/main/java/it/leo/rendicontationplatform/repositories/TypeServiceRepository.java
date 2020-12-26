package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.TypeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TypeServiceRepository extends JpaRepository<TypeService, Integer> {
    TypeService findTypeServiceById(int id);
    
    @Query("SELECT t FROM TypeService t WHERE t.enabled = true")
    List<TypeService> findAllTypeServiceEnabled();
}

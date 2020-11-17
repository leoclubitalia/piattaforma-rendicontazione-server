package it.leo.rendicontationplatform.repositories;


import it.leo.rendicontationplatform.entities.TypeActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TypeActivityRepository extends JpaRepository<TypeActivity, Integer> {
    @Query("SELECT t FROM TypeActivity t WHERE t.enabled = true")
    List<TypeActivity> findAllTypeActivityEnabled();
}

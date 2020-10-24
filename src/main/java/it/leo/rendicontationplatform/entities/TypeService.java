package it.leo.rendicontationplatform.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "type_service", schema = "rendicontation")
public class TypeService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "title", nullable = true, length = 70)
    private String title;

    @JsonIgnore
    @ManyToMany(mappedBy = "typesService", cascade = {CascadeType.PERSIST})
    private Set<Service> services;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }


}
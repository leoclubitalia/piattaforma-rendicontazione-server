package it.leo.rendicontationplatform.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "competence_area", schema = "rendicontation")
public class CompetenceArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "name", nullable = false, length = 70)
    private String name;

    @Basic
    @JsonIgnore
    @Column(name = "enabled", nullable = true)
    private boolean enabled;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "competence_area_service", joinColumns = @JoinColumn(name = "competence_area"), inverseJoinColumns = @JoinColumn(name = "service"))
    private Set<Service> services;


    public CompetenceArea(){}

    public CompetenceArea(int id){
        this.id = id;
    }


}

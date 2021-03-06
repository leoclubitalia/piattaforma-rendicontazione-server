package it.leo.rendicontationplatform.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "cap", schema = "rendicontation")
public class Cap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "number", nullable = false, length = 7)
    private String number;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "city_cap", joinColumns = @JoinColumn(name = "cap"), inverseJoinColumns = @JoinColumn(name = "city"))
    private Set<City> cities;


}

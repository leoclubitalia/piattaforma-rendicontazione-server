package it.leo.rendicontationplatform.entities;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "city", schema = "rendicontation")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "city_cap", joinColumns = @JoinColumn(name = "city"), inverseJoinColumns = @JoinColumn(name = "cap"))
    private Set<Cap> caps;


    public City(){}

    public City(int id){
        this.id = id;
    }


}




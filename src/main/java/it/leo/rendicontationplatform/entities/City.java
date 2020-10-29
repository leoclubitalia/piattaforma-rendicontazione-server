package it.leo.rendicontationplatform.entities;


import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "city", schema = "rendicontation")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "city_cap", joinColumns = @JoinColumn(name = "city"), inverseJoinColumns = @JoinColumn(name = "cap"))
    private Set<Cap> caps;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Cap> getCaps() {
        return caps;
    }

    public void setCaps(Set<Cap> caps) {
        this.caps = caps;
    }


}




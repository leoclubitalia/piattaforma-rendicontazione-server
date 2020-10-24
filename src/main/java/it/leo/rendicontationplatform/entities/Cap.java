package it.leo.rendicontationplatform.entities;


import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "cap", schema = "rendicontation")
public class Cap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "number", nullable = true, length = 7)
    private String number;


    @ManyToMany(mappedBy = "caps", cascade = {CascadeType.PERSIST})
    private Set<City> cities;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }


}

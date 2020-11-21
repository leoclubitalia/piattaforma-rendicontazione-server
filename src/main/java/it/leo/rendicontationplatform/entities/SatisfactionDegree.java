package it.leo.rendicontationplatform.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "satisfaction_degree", schema = "rendicontation")
public class SatisfactionDegree {
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


    public SatisfactionDegree(){}

    public SatisfactionDegree(int id){
        this.id = id;
    }


}

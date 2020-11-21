package it.leo.rendicontationplatform.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "type_activity", schema = "rendicontation")
public class TypeActivity {
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
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "r_type_activity", joinColumns = @JoinColumn(name = "activity"), inverseJoinColumns = @JoinColumn(name = "type_activity"))
    private Set<Activity> activities;


    public TypeActivity(){}

    public TypeActivity(int id){
        this.id = id;
    }


}

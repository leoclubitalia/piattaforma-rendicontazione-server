package it.leo.rendicontationplatform.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "club", schema = "rendicontation")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @JsonIgnore
    @Column(name = "enabled", nullable = true)
    private boolean enabled;

    @Basic
    @Column(name = "name", nullable = true, length = 150)
    private String name;

    @Basic
    @Column(name = "email", nullable = true, length = 150)
    private String email;

    @Basic
    @Column(name = "foundation_date", nullable = true)
    private Date foundationDate;

    @Basic
    @Column(name = "current_members", nullable = true)
    private Integer currentMembers;

    @Basic
    @Column(name = "aspirant_members", nullable = true)
    private Integer aspirantMembers;

    @ManyToOne
    @JoinColumn(name = "city")
    private City city;

    @ManyToOne
    @JoinColumn(name = "district")
    private District district;

    @Version
    @Column(name = "version", nullable = false)
    @JsonIgnore
    private long version;


    public Club(){}

    public Club(int id){
        this.id = id;
    }


}
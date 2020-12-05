package it.leo.rendicontationplatform.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "service", schema = "rendicontation")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Basic
    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Basic
    @Column(name = "date", nullable = false)
    @JsonFormat(pattern="ddMMyyyy")
    private Date date;

    @Basic
    @Column(name = "quantity_participants", nullable = true)
    private Integer quantityParticipants;

    @Basic
    @Column(name = "duration", nullable = true)
    private Integer duration;

    @Basic
    @Column(name = "other_associations", nullable = true, length = 400)
    private String otherAssociations;

    @Basic
    @Column(name = "money_or_material_collected", nullable = true)
    private String moneyOrMaterialCollected;

    @Basic
    @Column(name = "quantity_served_people", nullable = true)
    private Integer quantityServedPeople;

    @Version
    @Column(name = "version", nullable = false)
    @JsonIgnore
    private long version;

    @ManyToOne
    @JoinColumn(name = "city")
    private City city;

    @ManyToOne
    @JoinColumn(name = "club")
    private Club club;

    @ManyToOne
    @JoinColumn(name = "satisfaction_degree")
    private SatisfactionDegree satisfactionDegree;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "r_type_service", joinColumns = @JoinColumn(name = "service"), inverseJoinColumns = @JoinColumn(name = "type_service"))
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private Set<TypeService> typesService;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "competence_area_service", joinColumns = @JoinColumn(name = "service"), inverseJoinColumns = @JoinColumn(name = "competence_area"))
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private Set<CompetenceArea> competenceAreasService;


}

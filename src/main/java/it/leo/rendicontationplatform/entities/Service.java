package it.leo.rendicontationplatform.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "service", schema = "rendicontation")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "title", nullable = true, length = 50)
    private String title;

    @Basic
    @Column(name = "description", nullable = true, length = 500)
    private String description;

    @Basic
    @Column(name = "date", nullable = true)
    @JsonFormat(pattern="ddMMyyyy")
    private Date date;

    @Basic
    @Column(name = "quantity_participants", nullable = true)
    private int quantityParticipants;

    @Basic
    @Column(name = "duration", nullable = true)
    private int duration;

    @Basic
    @Column(name = "other_associations", nullable = true, length = 400)
    private String otherAssociations;

    @Basic
    @Column(name = "money_raised", nullable = true)
    private float moneyRaised;

    @Basic
    @Column(name = "quantity_served_people", nullable = true)
    private int quantityServedPeople;

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

    @ManyToMany
    @JoinTable(name = "r_type_service", joinColumns = @JoinColumn(name = "service"), inverseJoinColumns = @JoinColumn(name = "type_service"))
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private Set<TypeService> typesService;

    @ManyToMany
    @JoinTable(name = "competence_area_service", joinColumns = @JoinColumn(name = "service"), inverseJoinColumns = @JoinColumn(name = "competence_area"))
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private Set<CompetenceArea> competenceAreasService;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantityParticipants() {
        return quantityParticipants;
    }

    public void setQuantityParticipants(int quantityParticipants) {
        this.quantityParticipants = quantityParticipants;
    }

    public SatisfactionDegree getSatisfactionDegree() {
        return satisfactionDegree;
    }

    public void setSatisfactionDegree(SatisfactionDegree satisfactionDegree) {
        this.satisfactionDegree = satisfactionDegree;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getOtherAssociations() {
        return otherAssociations;
    }

    public void setOtherAssociations(String otherAssociations) {
        this.otherAssociations = otherAssociations;
    }

    public float getMoneyRaised() {
        return moneyRaised;
    }

    public void setMoneyRaised(float moneyRaised) {
        this.moneyRaised = moneyRaised;
    }

    public int getQuantityServedPeople() {
        return quantityServedPeople;
    }

    public void setQuantityServedPeople(int quantityServedPeople) {
        this.quantityServedPeople = quantityServedPeople;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Set<TypeService> getTypesService() {
        return typesService;
    }

    public void setTypesService(Set<TypeService> typesService) {
        this.typesService = typesService;
    }

    public Set<CompetenceArea> getCompetenceAreasService() {
        return competenceAreasService;
    }

    public void setCompetenceAreasService(Set<CompetenceArea> competenceAreasService) {
        this.competenceAreasService = competenceAreasService;
    }


}

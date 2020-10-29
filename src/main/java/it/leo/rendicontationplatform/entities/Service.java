package it.leo.rendicontationplatform.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private Date date;

    @Basic
    @Column(name = "quantity_participants", nullable = true)
    private int quantityParticipants;

    @Basic
    @Column(name = "satisfaction_degree", nullable = true)
    private int satisfactionDegree;

    @Basic
    @Column(name = "impact", nullable = true)
    private int impact;

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
    @JsonIgnore
    @Column(name = "version", nullable = false)
    private long version;

    @ManyToOne
    @JoinColumn(name = "city")
    private City city;

    @ManyToOne
    @JoinColumn(name = "club")
    private Club club;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "r_type_service", joinColumns = @JoinColumn(name = "service"), inverseJoinColumns = @JoinColumn(name = "type_service"))
    private Set<TypeService> typesService;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "competence_area_service", joinColumns = @JoinColumn(name = "service"), inverseJoinColumns = @JoinColumn(name = "competence_area"))
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

    public int getSatisfactionDegree() {
        return satisfactionDegree;
    }

    public void setSatisfactionDegree(int satisfactionDegree) {
        this.satisfactionDegree = satisfactionDegree;
    }

    public int getImpact() {
        return impact;
    }

    public void setImpact(int impact) {
        this.impact = impact;
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

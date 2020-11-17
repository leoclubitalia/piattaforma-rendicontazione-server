package it.leo.rendicontationplatform.entities;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "research_service", schema = "rendicontation")
public class ResearchService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "start_date")
    private Date startDate;

    @Basic
    @Column(name = "end_date")
    private Date endDate;

    @Basic
    @Column(name = "quantity_participants")
    private Integer quantityParticipants;

    @ManyToOne
    @JoinColumn(name = "satisfaction_degree")
    private SatisfactionDegree satisfactionDegree;

    @Basic
    @Column(name = "duration")
    private Integer duration;

    @Basic
    @Column(name = "other_associations")
    private String otherAssociations;

    @Basic
    @Column(name = "min_money_raised")
    private Float minMoneyRaised;

    @Basic
    @Column(name = "max_money_raised")
    private Float maxMoneyRaised;

    @Basic
    @Column(name = "quantity_served_people")
    private Integer quantityServedPeople;

    @ManyToOne
    @JoinColumn(name = "city")
    private City city;

    @ManyToOne
    @JoinColumn(name = "club")
    private Club club;

    @ManyToOne
    @JoinColumn(name = "type_service")
    private TypeService typeService;

    @ManyToOne
    @JoinColumn(name = "competence_area")
    private CompetenceArea competenceArea;

    @ManyToOne
    @JoinColumn(name = "district")
    private District district;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getQuantityParticipants() {
        return this.quantityParticipants;
    }

    public void setQuantityParticipants(Integer quantityParticipants) {
        this.quantityParticipants = quantityParticipants;
    }

    public SatisfactionDegree getSatisfactionDegree() {
        return this.satisfactionDegree;
    }

    public void setSatisfactionDegree(SatisfactionDegree satisfactionDegree) {
        this.satisfactionDegree = satisfactionDegree;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getOtherAssociations() {
        return this.otherAssociations;
    }

    public void setOtherAssociations(String otherAssociations) {
        this.otherAssociations = otherAssociations;
    }

    public Float getMinMoneyRaised() {
        return this.minMoneyRaised;
    }

    public void setMinMoneyRaised(Float minMoneyRaised) {
        this.minMoneyRaised = minMoneyRaised;
    }

    public Float getMaxMoneyRaised() {
        return this.maxMoneyRaised;
    }

    public void setMaxMoneyRaised(Float maxMoneyRaised) {
        this.maxMoneyRaised = maxMoneyRaised;
    }

    public Integer getQuantityServedPeople() {
        return this.quantityServedPeople;
    }

    public void setQuantityServedPeople(Integer quantityServedPeople) {
        this.quantityServedPeople = quantityServedPeople;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Club getClub() {
        return this.club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public TypeService getTypeService() {
        return this.typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    public CompetenceArea getCompetenceArea() {
        return this.competenceArea;
    }

    public void setCompetenceArea(CompetenceArea competenceArea) {
        this.competenceArea = competenceArea;
    }

    public District getDistrict() {
        return this.district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }


}

package it.leo.rendicontationplatform.entities;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "research_activity", schema = "rendicontation")
public class ResearchActivity {
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
    @Column(name = "quantity_leo")
    private Integer quantityLeo;

    @ManyToOne
    @JoinColumn(name = "satisfaction_degree")
    private SatisfactionDegree satisfactionDegree;

    @Basic
    @Column(name = "lions_participation")
    private boolean lionsParticipation;

    @ManyToOne
    @JoinColumn(name = "city")
    private City city;

    @ManyToOne
    @JoinColumn(name = "club")
    private Club club;

    @ManyToOne
    @JoinColumn(name = "type_activity")
    private TypeActivity typeActivity;

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

    public Integer getQuantityLeo() {
        return this.quantityLeo;
    }

    public void setQuantityLeo(Integer quantityLeo) {
        this.quantityLeo = quantityLeo;
    }

    public SatisfactionDegree getSatisfactionDegree() {
        return this.satisfactionDegree;
    }

    public void setSatisfactionDegree(SatisfactionDegree satisfactionDegree) {
        this.satisfactionDegree = satisfactionDegree;
    }

    public boolean getLionsParticipation() {
        return this.lionsParticipation;
    }

    public void setLionsParticipation(boolean lionsParticipation) {
        this.lionsParticipation = lionsParticipation;
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

    public TypeActivity getTypeActivity() {
        return this.typeActivity;
    }

    public void setTypeActivity(TypeActivity typeActivity) {
        this.typeActivity = typeActivity;
    }

    public District getDistrict() {
        return this.district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }


}

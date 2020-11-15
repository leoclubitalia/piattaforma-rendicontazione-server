package it.leo.rendicontationplatform.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "activity", schema = "rendicontation")
public class Activity {
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
    @Column(name = "quantity_leo", nullable = true)
    private int quantityLeo;

    @Basic
    @Column(name = "lions_participation", nullable = true)
    private boolean lionsParticipation;

    @ManyToOne
    @JoinColumn(name = "satisfaction_degree")
    private SatisfactionDegree satisfactionDegree;

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

    @ManyToMany
    @JoinTable(name = "r_type_activity", joinColumns = @JoinColumn(name = "type_activity"), inverseJoinColumns = @JoinColumn(name = "activity"))
    private Set<TypeActivity> typesActivity;


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

    public int getQuantityLeo() {
        return quantityLeo;
    }

    public void setQuantityLeo(int quantityLeo) {
        this.quantityLeo = quantityLeo;
    }

    public boolean isLionsParticipation() {
        return lionsParticipation;
    }

    public void setLionsParticipation(boolean lionsParticipation) {
        this.lionsParticipation = lionsParticipation;
    }

    public SatisfactionDegree getSatisfactionDegree() {
        return satisfactionDegree;
    }

    public void setSatisfactionDegree(SatisfactionDegree satisfactionDegree) {
        this.satisfactionDegree = satisfactionDegree;
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

    public Set<TypeActivity> getTypesActivity() {
        return typesActivity;
    }

    public void setTypesActivity(Set<TypeActivity> typesActivity) {
        this.typesActivity = typesActivity;
    }


}

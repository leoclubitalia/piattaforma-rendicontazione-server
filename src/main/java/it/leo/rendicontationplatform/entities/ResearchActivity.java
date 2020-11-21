package it.leo.rendicontationplatform.entities;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
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
    private Boolean lionsParticipation;

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


}

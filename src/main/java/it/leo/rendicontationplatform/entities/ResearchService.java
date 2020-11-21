package it.leo.rendicontationplatform.entities;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
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


}

package codesquad.airbnb.accomodation.domain;

import javax.persistence.*;

@Entity
public class AccommodationProvide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @ManyToOne
    @JoinColumn(name = "provide_id")
    private Provide provide;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int count;
}

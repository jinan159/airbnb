package codesquad.airbnb.accomodation.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private long price;

    @Embedded
    private Image image;

    private Point point;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "accommodation", fetch = FetchType.LAZY)
    private List<AccommodationProvide> accommodationProvides;
}

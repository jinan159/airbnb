package codesquad.airbnb.accomodation;

import codesquad.airbnb.common.Money;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.geo.Point;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Embedded
    @Column(nullable = false)
    private Money price;

    @Embedded
    private Image image;

    private Point point;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provides_id")
    private Provides provides;
}

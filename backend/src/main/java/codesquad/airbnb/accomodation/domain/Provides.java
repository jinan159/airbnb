package codesquad.airbnb.accomodation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor // for @Entity
public class Provides {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int capacity;
    private Integer bedRooms;
    private Integer beds;
    private Integer washRooms;
    private Boolean hasKitchin;
    private Boolean hasWifi;
    private Boolean hasAirConditioner;
    private Boolean hasHairDryer;
}

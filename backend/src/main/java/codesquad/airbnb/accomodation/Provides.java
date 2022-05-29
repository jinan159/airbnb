package codesquad.airbnb.accomodation;

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
    private int capacity;
    private int bedRooms;
    private int beds;
    private int washRooms;
    private boolean hasKitchin;
    private boolean hasWifi;
    private boolean hasAirConditioner;
    private boolean hasHairDryer;
}

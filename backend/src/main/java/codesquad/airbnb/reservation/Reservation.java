package codesquad.airbnb.reservation;

import codesquad.airbnb.accomodation.domain.Accommodation;
import codesquad.airbnb.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor // for @Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id", nullable = false)
    private Accommodation accommodation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Embedded
    @Column(nullable = false)
    private Schedule schedule;

    public static Reservation createNewReservation(Accommodation accommodation,Member member, Schedule schedule) {
        return new Reservation(null, accommodation, member, schedule);
    }
}

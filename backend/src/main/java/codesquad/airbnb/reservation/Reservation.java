package codesquad.airbnb.reservation;

import codesquad.airbnb.accomodation.Accommodation;
import codesquad.airbnb.member.Member;

import javax.persistence.*;

@Entity
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private Schedule schedule;
}

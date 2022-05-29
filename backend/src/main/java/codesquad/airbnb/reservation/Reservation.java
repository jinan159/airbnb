package codesquad.airbnb.reservation;

import codesquad.airbnb.accomodation.Accommodation;
import codesquad.airbnb.member.Member;

import javax.persistence.*;

@Entity
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}

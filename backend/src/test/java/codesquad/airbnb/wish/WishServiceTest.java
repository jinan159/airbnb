package codesquad.airbnb.wish;

import codesquad.airbnb.accomodation.domain.Accommodation;
import codesquad.airbnb.accomodation.exception.AccommodationNotFoundException;
import codesquad.airbnb.accomodation.repository.AccommodationRepository;
import codesquad.airbnb.member.Member;
import codesquad.airbnb.member.MemberRepository;
import codesquad.airbnb.member.exception.MemberNotFoundException;
import codesquad.airbnb.wish.dto.WishResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class WishServiceTest {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private WishRepository wishRepository;

    @Autowired
    private WishService wishService;

    @BeforeEach
    void setUp() {
        accommodationRepository.deleteAll();
        memberRepository.deleteAll();
        wishRepository.deleteAll();
    }

    @Test
    void 존재하는_숙소는_위시리스트를_추가할_수_있다() {
        // given
        Accommodation savedAccommodation = createNewAccommodation();
        Member savedMember = createNewMember();

        // when
        WishResponse wishResponse = wishService.addWish(savedAccommodation.getId(), savedMember.getId());

        // then
        assertThat(wishResponse).isNotNull();
        assertThat(wishResponse.getAccommodationId()).isEqualTo(savedAccommodation.getId());
        assertThat(wishResponse.getMemberId()).isEqualTo(savedMember.getId());
    }

    @Test
    void 존재하는_위시리스트는_제거할_수_있다() {
        // given
        Accommodation savedAccommodation = createNewAccommodation();
        Member savedMember = createNewMember();
        WishResponse wishResponse = wishService.addWish(savedAccommodation.getId(), savedMember.getId());

        // when
        wishService.removeWish(wishResponse.getId());
        Optional<Wish> findResult = wishRepository.findById(wishResponse.getId());

        // then
        assertThat(findResult.isPresent()).isFalse();
    }

    @Test
    void 존재하지_않는_숙소는_위시리스트를_추가하면_예외가_발생한다() {
        // given
        Long notExistAccommodationId = 1L;
        Member savedMember = createNewMember();

        // then
        assertThatThrownBy(() -> wishService.addWish(notExistAccommodationId, savedMember.getId()))
                .isInstanceOf(AccommodationNotFoundException.class)
                .hasMessageContaining("존재하지 않는 숙소입니다.");
    }

    @Test
    void 존재하지_않는_회원은_위시리스트를_추가하면_예외가_발생한다() {
        // given
        Accommodation savedAccommodation = createNewAccommodation();
        Long notExistMemberId = 1L;

        // then
        assertThatThrownBy(() -> wishService.addWish(savedAccommodation.getId(), notExistMemberId))
                .isInstanceOf(MemberNotFoundException.class)
                .hasMessageContaining("존재하지 않는 회원입니다.");
    }

    private Accommodation createNewAccommodation() {
        return accommodationRepository.save(new Accommodation(null, "test hotel" , 10000, null, null, null, null));
    }

    private Member createNewMember() {
        return memberRepository.save(new Member(null, "test@email.com", null));
    }
}
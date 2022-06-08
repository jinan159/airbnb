package codesquad.airbnb.wish;

import codesquad.airbnb.accomodation.domain.Accommodation;
import codesquad.airbnb.accomodation.exception.AccommodationNotFoundException;
import codesquad.airbnb.accomodation.repository.AccommodationRepository;
import codesquad.airbnb.member.Member;
import codesquad.airbnb.member.MemberRepository;
import codesquad.airbnb.member.exception.MemberNotFoundException;
import codesquad.airbnb.wish.dto.WishAddRequest;
import codesquad.airbnb.wish.dto.WishResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
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
        wishRepository.deleteAll();
        memberRepository.deleteAll();
        accommodationRepository.deleteAll();
    }

    @Nested
    @DisplayName("위시 리스트 등록/삭제 테스트")
    class AddRemoveTest{
        @Test
        void 존재하는_숙소는_위시리스트를_추가할_수_있다() {
            // given
            Accommodation savedAccommodation = createNewAccommodation();
            Member savedMember = createNewMember();
            WishAddRequest wishAddRequest = new WishAddRequest(savedAccommodation.getId(), savedMember.getId());

            // when
            WishResponse wishResponse = wishService.addWish(wishAddRequest);

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
            WishResponse wishResponse = wishService.addWish(new WishAddRequest(savedAccommodation.getId(), savedMember.getId()));

            // when
            wishService.removeWish(wishResponse.getId());
            Optional<Wish> findResult = wishRepository.findById(wishResponse.getId());

            // then
            assertThat(findResult.isPresent()).isFalse();
        }

        @Test
        void 존재하지_않는_숙소는_위시리스트를_추가하면_예외가_발생한다() {
            // given
            long notExistAccommodationId = 1L;
            Member savedMember = createNewMember();

            // then
            assertThatThrownBy(() -> wishService.addWish(new WishAddRequest(notExistAccommodationId, savedMember.getId())))
                    .isInstanceOf(AccommodationNotFoundException.class)
                    .hasMessageContaining("존재하지 않는 숙소입니다.");
        }

        @Test
        void 존재하지_않는_회원은_위시리스트를_추가하면_예외가_발생한다() {
            // given
            Accommodation savedAccommodation = createNewAccommodation();
            long notExistMemberId = 1L;

            // then
            assertThatThrownBy(() -> wishService.addWish(new WishAddRequest(savedAccommodation.getId(), notExistMemberId)))
                    .isInstanceOf(MemberNotFoundException.class)
                    .hasMessageContaining("존재하지 않는 회원입니다.");
        }
    }

    @Test
    void 존재하는_위시리스트를_조회하면_위시리스트_목록이_반환된다() {
        // given
        Accommodation savedAccommodation = accommodationRepository.save(createNewAccommodation());
        Member savedMember = memberRepository.save(createNewMember());
        wishRepository.save(Wish.createNewWish(savedAccommodation.getId(), savedMember.getId()));

        // when
        List<Wish> wishes = wishRepository.findAllByAccommodationAndMember(
                List.of(savedAccommodation.getId()), savedMember.getId());

        // then
        assertThat(wishes).isNotNull();
        assertThat(wishes).isNotEmpty();
        assertThat(wishes).size().isEqualTo(1);
        assertThat(wishes.get(0).getAccommodationId()).isEqualTo(savedAccommodation.getId());
        assertThat(wishes.get(0).getMemberId()).isEqualTo(savedMember.getId());
    }

    @Test
    void 위시리스트로_등록되지_않은_숙소로_위시리스트를_조회하면_빈_목록이_반환된다() {
        // given
        Accommodation savedAccommodation = accommodationRepository.save(createNewAccommodation());
        Member savedMember = memberRepository.save(createNewMember());
        long unsavedAccommodationId = 0;
        wishRepository.save(Wish.createNewWish(savedAccommodation.getId(), savedMember.getId()));

        // when
        List<Wish> wishes = wishRepository.findAllByAccommodationAndMember(
                List.of(unsavedAccommodationId), savedMember.getId());

        // then
        assertThat(wishes).isNotNull();
        assertThat(wishes).isEmpty();
    }

    @Test
    void 위시리스트를_등록하지_않은_회원으로_위시리스트를_조회하면_빈_목록이_반환된다() {
        // given
        Accommodation savedAccommodation = accommodationRepository.save(createNewAccommodation());
        Member savedMember = memberRepository.save(createNewMember());
        long unsavedMemberId = 0;
        wishRepository.save(Wish.createNewWish(savedAccommodation.getId(), savedMember.getId()));

        // when
        List<Wish> wishes = wishRepository.findAllByAccommodationAndMember(
                List.of(savedAccommodation.getId()), unsavedMemberId);

        // then
        assertThat(wishes).isNotNull();
        assertThat(wishes).isEmpty();
    }

    private Accommodation createNewAccommodation() {
        return accommodationRepository.save(Accommodation.createNewAccommodation("test hotel" , 10000, null, null, null));
    }

    private Member createNewMember() {
        return memberRepository.save(Member.createNewMember("test@email.com", "jinan"));
    }
}
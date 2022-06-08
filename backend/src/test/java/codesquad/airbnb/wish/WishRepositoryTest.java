package codesquad.airbnb.wish;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class WishRepositoryTest {

    @Autowired
    private WishRepository wishRepository;

    @BeforeEach
    void setUp() {
        wishRepository.deleteAll();
    }

    @Test
    void 위시_리스트로_등록된_숙소아이디_와_회원아이디로_조회하면_위시리스트_목록이_반환된다() {
        // given
        long savedAccommodationId = 1L;
        long savedMemberId = 2L;
        wishRepository.save(Wish.createNewWish(savedAccommodationId, savedMemberId));

        // when
        List<Wish> wishes = wishRepository.findAllByAccommodationAndMember(List.of(savedAccommodationId), savedMemberId);

        // then
        assertThat(wishes).isNotNull();
        assertThat(wishes).isNotEmpty();
        assertThat(wishes).size().isEqualTo(1);
        assertThat(wishes.get(0).getAccommodationId()).isEqualTo(savedAccommodationId);
        assertThat(wishes.get(0).getMemberId()).isEqualTo(savedMemberId);
    }

    @Test
    void 위시_리스트로_등록되지_않은_숙소아이디로_조회하면_빈_목록이_반환된다() {
        // given
        long savedAccommodationId = 1L;
        long savedMemberId = 2L;
        long unsavedAccommodationId = 3L;
        wishRepository.save(Wish.createNewWish(savedAccommodationId, savedMemberId));

        // when
        List<Wish> wishes = wishRepository.findAllByAccommodationAndMember(List.of(unsavedAccommodationId), savedMemberId);

        // then
        assertThat(wishes).isNotNull();
        assertThat(wishes).isEmpty();
    }

    @Test
    void 위시_리스트를_등록한적_없는_회원아이디로_조회하면_빈_목록이_반환된다() {
        // given
        long savedAccommodationId = 1L;
        long savedMemberId = 2L;
        long unsavedMemberId = 3L;
        wishRepository.save(Wish.createNewWish(savedAccommodationId, savedMemberId));

        // when
        List<Wish> wishes = wishRepository.findAllByAccommodationAndMember(List.of(savedAccommodationId), unsavedMemberId);

        // then
        assertThat(wishes).isNotNull();
        assertThat(wishes).isEmpty();
    }
}
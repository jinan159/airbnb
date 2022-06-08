package codesquad.airbnb.accomodation.service;

import codesquad.airbnb.accomodation.dto.AccommodationResponse;
import codesquad.airbnb.accomodation.dto.AccommodationSearchRequest;
import codesquad.airbnb.accomodation.dto.WishedAccommodationResponse;
import codesquad.airbnb.wish.WishService;
import codesquad.airbnb.wish.dto.WishResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class WishedAccommodationServiceTest {

    @InjectMocks
    private WishedAccommodationService wishedAccommodationService;

    @Mock
    private AccommodationService accommodationService;

    @Mock
    private WishService wishService;

    @Test
    void 위시_리스트가_추가된_숙소는_위시리스트_정보가_함께_반환된다() {
        // given
        AccommodationResponse expectedAccommodation =
                new AccommodationResponse(1, "test hotel", 10000, "https://image.com", 37.123, 127.123, "서울시 송파구", Collections.emptyList());
        WishResponse expectedWishResponse = new WishResponse(1, 1, 1);

        LocalDate startDate = LocalDate.of(2022,6,1);
        LocalDate endDate = LocalDate.of(2022,6,5);
        int visitors = 3;

        given(accommodationService.findNotReservedAccommodations(any()))
                .willReturn(List.of(expectedAccommodation));
        given(wishService.findAllWishesOfAccommodationAndMember(anyList(), any()))
                .willReturn(List.of(expectedWishResponse));

        // when
        List<WishedAccommodationResponse> wishedAccommodations = wishedAccommodationService.findWishedAccommodations(new AccommodationSearchRequest(startDate, endDate, visitors));

        // then
        assertThat(wishedAccommodations).isNotNull();
        assertThat(wishedAccommodations).isNotEmpty();
        assertThat(wishedAccommodations).size().isEqualTo(1);
        WishedAccommodationResponse wishedAccommodation = wishedAccommodations.get(0);
        assertThat(wishedAccommodation.getAccommodationResponse().getId()).isEqualTo(expectedAccommodation.getId());
        assertThat(wishedAccommodation.getWish().getAccommodationId()).isEqualTo(expectedAccommodation.getId());
    }

    @Test
    void 위시_리스트에_추가되지_않은_숙소는_숙소정보만_반환된다() {
        // given
        AccommodationResponse expectedAccommodation =
                new AccommodationResponse(1, "test hotel", 10000, "https://image.com", 37.123, 127.123, "서울시 송파구", Collections.emptyList());

        LocalDate startDate = LocalDate.of(2022,6,1);
        LocalDate endDate = LocalDate.of(2022,6,5);
        int visitors = 3;

        given(accommodationService.findNotReservedAccommodations(any()))
                .willReturn(List.of(expectedAccommodation));
        given(wishService.findAllWishesOfAccommodationAndMember(anyList(), any()))
                .willReturn(Collections.emptyList());

        // when
        List<WishedAccommodationResponse> wishedAccommodations = wishedAccommodationService.findWishedAccommodations(new AccommodationSearchRequest(startDate, endDate, visitors));

        // then
        assertThat(wishedAccommodations).isNotNull();
        assertThat(wishedAccommodations).isNotEmpty();
        assertThat(wishedAccommodations).size().isEqualTo(1);
        WishedAccommodationResponse wishedAccommodation = wishedAccommodations.get(0);
        assertThat(wishedAccommodation.getAccommodationResponse().getId()).isEqualTo(expectedAccommodation.getId());
        assertThat(wishedAccommodation.getWish()).isNull();
    }
}
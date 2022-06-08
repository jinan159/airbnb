package codesquad.airbnb.accomodation.service;

import codesquad.airbnb.accomodation.dto.AccommodationResponse;
import codesquad.airbnb.accomodation.dto.AccommodationSearchRequest;
import codesquad.airbnb.accomodation.dto.WishedAccommodationResponse;
import codesquad.airbnb.wish.WishService;
import codesquad.airbnb.wish.dto.WishResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishedAccommodationService {

    private static final long DEFAULT_MEMBER_ID = 0;

    public final AccommodationService accommodationService;
    public final WishService wishService;

    @Transactional(readOnly = true)
    public List<WishedAccommodationResponse> findWishedAccommodations(AccommodationSearchRequest searchRequest) {
        List<AccommodationResponse> notReservedAccommodations = accommodationService.findNotReservedAccommodations(searchRequest);
        List<Long> accommodationIds = notReservedAccommodations.stream()
                .mapToLong(AccommodationResponse::getId)
                .boxed()
                .collect(Collectors.toUnmodifiableList());

        // TODO : 2022-06-08 회원 정보가 추가되면 DEFAULT_MEMBER_ID 제거
        Map<Long, WishResponse> accommodationWish = wishService.findAllWishesOfAccommodationAndMember(accommodationIds, DEFAULT_MEMBER_ID).stream()
                .collect(Collectors.toMap(WishResponse::getAccommodationId, wish -> wish));

        return notReservedAccommodations.stream()
                .map(acc -> new WishedAccommodationResponse(acc, accommodationWish.get(acc.getId())))
                .collect(Collectors.toUnmodifiableList());
    }
}

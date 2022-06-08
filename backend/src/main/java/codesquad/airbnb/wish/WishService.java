package codesquad.airbnb.wish;

import codesquad.airbnb.accomodation.exception.AccommodationNotFoundException;
import codesquad.airbnb.accomodation.repository.AccommodationRepository;
import codesquad.airbnb.member.MemberRepository;
import codesquad.airbnb.member.exception.MemberNotFoundException;
import codesquad.airbnb.wish.dto.WishAddRequest;
import codesquad.airbnb.wish.dto.WishResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
// TODO : 2022-06-07 전체 Transactional 로 변경하고, 읽기 메소드만 readOnly 옵션과 함께 붙여주기
public class WishService {

    private final WishRepository wishRepository;
    private final AccommodationRepository accommodationRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public WishResponse addWish(WishAddRequest request) {
        validateAccommodationExists(request.getAccommodationId());
        validateMemberExists(request.getMemberId());

        Wish savedWish = wishRepository.save(Wish.createNewWish(request.getAccommodationId(), request.getMemberId()));
        return WishResponse.from(savedWish);
    }

    @Transactional
    public void removeWish(long id) {
        wishRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<WishResponse> findAllWishesOfAccommodationAndMember(List<Long> accommodationIds, Long memberId) {
        return wishRepository.findAllByAccommodationAndMember(accommodationIds, memberId).stream()
                .map(WishResponse::from)
                .collect(Collectors.toUnmodifiableList());
    }

    private void validateMemberExists(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new MemberNotFoundException();
        }
    }

    private void validateAccommodationExists(Long accommodationId) {
        if (!accommodationRepository.existsById(accommodationId)) {
            throw new AccommodationNotFoundException();
        }
    }

}

package codesquad.airbnb.wish;

import codesquad.airbnb.accomodation.exception.AccommodationNotFoundException;
import codesquad.airbnb.accomodation.repository.AccommodationRepository;
import codesquad.airbnb.member.MemberRepository;
import codesquad.airbnb.member.exception.MemberNotFoundException;
import codesquad.airbnb.wish.dto.WishResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;
    private final AccommodationRepository accommodationRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public WishResponse addWish(Long accommodationId, Long memberId) {
        validateAccommodationExists(accommodationId);
        validateMemberExists(memberId);

        return WishResponse.from(wishRepository.save(new Wish(null, accommodationId, memberId)));
    }

    @Transactional
    public void removeWish(long id) {
        wishRepository.deleteById(id);
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

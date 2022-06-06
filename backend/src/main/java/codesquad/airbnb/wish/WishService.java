package codesquad.airbnb.wish;

import codesquad.airbnb.accomodation.exception.AccommodationNotFoundException;
import codesquad.airbnb.accomodation.repository.AccommodationRepository;
import codesquad.airbnb.member.MemberRepository;
import codesquad.airbnb.member.exception.MemberNotFoundException;
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
    public Wish addWish(Long accommodationId, Long memberId) {
        validateAccommodationExists(accommodationId);
        validateMemberExists(memberId);

        return wishRepository.save(new Wish(null, accommodationId, memberId));
    }

    @Transactional
    public void removeWish(Long accommodationId, Long memberId) {
        wishRepository.deleteByAccommodationIdAndMemberId(accommodationId, memberId);
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

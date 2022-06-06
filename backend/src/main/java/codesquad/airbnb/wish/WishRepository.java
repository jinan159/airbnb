package codesquad.airbnb.wish;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Long> {

    void deleteByAccommodationIdAndMemberId(Long accommodationId, Long memberId);
}

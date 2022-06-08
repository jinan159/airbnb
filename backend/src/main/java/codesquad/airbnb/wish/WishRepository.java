package codesquad.airbnb.wish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishRepository extends JpaRepository<Wish, Long> {

    @Query("SELECT w FROM Wish w WHERE w.accommodationId in (:accommodationIds) AND w.memberId = :memberId")
    List<Wish> findAllByAccommodationAndMember(
            @Param("accommodationIds") List<Long> accommodationIds,
            @Param("memberId") Long memberId);

}

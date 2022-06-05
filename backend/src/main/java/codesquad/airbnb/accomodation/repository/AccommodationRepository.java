package codesquad.airbnb.accomodation.repository;

import codesquad.airbnb.accomodation.domain.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
}

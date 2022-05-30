package codesquad.airbnb.accomodation;

import codesquad.airbnb.accomodation.dto.PriceAndCountStatistics;
import codesquad.airbnb.accomodation.service.AccommodationStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accommodation")
public class AccommodationController {

    private final AccommodationStatisticsService dummyAccommodationStatisticsService;

    @GetMapping("/statistics/price")
    public PriceAndCountStatistics getAccommodationPriceStatistics() {
        return dummyAccommodationStatisticsService.getCachedPriceAndCountStatistics();
    }
}

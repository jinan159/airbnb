package codesquad.airbnb.accomodation;

import codesquad.airbnb.accomodation.dto.AccommodationResponse;
import codesquad.airbnb.accomodation.dto.AccommodationSearchRequest;
import codesquad.airbnb.accomodation.dto.PriceAndCountStatistics;
import codesquad.airbnb.accomodation.service.AccommodationService;
import codesquad.airbnb.accomodation.service.AccommodationStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accommodations")
public class AccommodationController {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final AccommodationStatisticsService dummyAccommodationStatisticsService;
    private final AccommodationService accommodationService;

    @GetMapping("/statistics/price")
    public PriceAndCountStatistics getAccommodationPriceStatistics() {
        return dummyAccommodationStatisticsService.getCachedPriceAndCountStatistics();
    }

    @GetMapping()
    public List<AccommodationResponse> getNotReservedAccommodations(
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "visitors", required = false) Integer visitors) {

        AccommodationSearchRequest searchRequest = new AccommodationSearchRequest(
                LocalDate.parse(startDate, DATE_FORMAT),
                LocalDate.parse(endDate, DATE_FORMAT),
                visitors);

        return accommodationService.findNotReservedAccommodations(searchRequest);
    }
}

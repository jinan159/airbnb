package codesquad.airbnb.accomodation.dto;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
public class PriceAndCountStatistics implements Iterable<PriceAndCount> {
    private final List<PriceAndCount> priceAndCountList;

    public List<PriceAndCount> getPriceAndCountList() {
        return Collections.unmodifiableList(priceAndCountList);
    }

    @Override
    public Iterator<PriceAndCount> iterator() {
        return Collections.unmodifiableList(priceAndCountList).iterator();
    }
}

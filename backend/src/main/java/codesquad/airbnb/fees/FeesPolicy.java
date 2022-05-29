package codesquad.airbnb.fees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class FeesPolicy {
    private final List<Fees> feesList = new ArrayList<>();

    public FeesPolicy(Fees ...fees) {
        feesList.addAll(Arrays.stream(fees)
                .collect(Collectors.toUnmodifiableList()));
    }

    public List<Fees> getFeesList() {
        return Collections.unmodifiableList(feesList);
    }
}

package codesquad.airbnb.accomodation.domain;

public enum ProvideElement {
    CAPACITY("최대 인원"),
    BED_ROOM("침실"),
    BED("침대"),
    WASH_ROOM("욕실"),
    KITCHEN("주방"),
    WIFI("무선 인터넷"),
    AIR_CONDITIONER("에어컨"),
    HAIR_DRYER("헤어드라이어");

    private final String name;

    ProvideElement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ProvideElement provideNameOf(String provideName) {

        for (ProvideElement provideElement : values()) {
            if (provideElement.getName().equals(provideName)) {
                return provideElement;
            }
        }

        throw new IllegalArgumentException("항목을 찾을 수 없습니다.(" + provideName + ")");
    }
}

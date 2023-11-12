package christmas.domain;

import java.util.Map;

public class EventDetail {
    private final Map<String, Integer> detail;

    public EventDetail(Map<String, Integer> detail) {
        this.detail = detail;
    }

    public Map<String, Integer> getDetail() {
        return detail;
    }
}

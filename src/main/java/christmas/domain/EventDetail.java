package christmas.domain;

import java.util.Map;

public class EventDetail {
    private final Map<String, Integer> eventDetail;

    public EventDetail(Map<String, Integer> eventDetail) {
        this.eventDetail = eventDetail;
    }

    public Map<String, Integer> getEventDetail() {
        return eventDetail;
    }
}

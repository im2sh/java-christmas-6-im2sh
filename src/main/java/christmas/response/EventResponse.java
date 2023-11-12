package christmas.response;

import christmas.domain.EventDetail;
import java.util.List;

public class EventResponse {
    private final List<EventDetail> event;

    public EventResponse(List<EventDetail> event) {
        this.event = event;
    }

    public List<EventDetail> getEvent() {
        return event;
    }
}

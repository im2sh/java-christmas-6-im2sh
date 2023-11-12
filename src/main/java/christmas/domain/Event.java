package christmas.domain;

import java.util.List;
import java.util.Map;

public class Event {
    private final List<EventDetail> event;

    public Event(List<EventDetail> event) {
        this.event = event;
    }

    public List<EventDetail> getEvent() {
        return event;
    }
}

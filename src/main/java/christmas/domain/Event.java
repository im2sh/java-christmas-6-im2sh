package christmas.domain;

import christmas.response.EventResponse;
import java.util.List;
import java.util.Map;

public class Event {
    private final List<EventDetail> event;

    public Event(List<EventDetail> event) {
        this.event = event;
    }

    public EventResponse toResponse(){
        return new EventResponse(event);
    }
}

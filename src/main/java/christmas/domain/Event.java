package christmas.domain;

import christmas.response.EventResponse;
import java.util.List;

public class Event {
    private final List<EventDetail> event;

    public Event(List<EventDetail> event) {
        this.event = event;
    }

    public EventResponse toResponse() {
        return new EventResponse(event);
    }

    public int calculateBenefitAmount() {
        return event.stream()
                .flatMap(eventDetail -> eventDetail.getDetail().values().stream())
                .mapToInt(Integer::intValue)
                .sum();
    }
}

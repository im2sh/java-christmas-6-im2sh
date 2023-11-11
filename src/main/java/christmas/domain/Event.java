package christmas.domain;

import java.util.List;
import java.util.Map;

public class Event {
    private final List<Map<String, Integer>> event;

    public Event(List<Map<String, Integer>> event) {
        this.event = event;
    }

    public List<Map<String, Integer>> getEvent() {
        return event;
    }
}

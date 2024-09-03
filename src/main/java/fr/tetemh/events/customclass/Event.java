package fr.tetemh.events.customclass;

import fr.tetemh.events.EventStatus;
import fr.tetemh.skycite.utils.Utils;
import lombok.Data;

@Data
public class Event {

    private final String name;
    private final String constantName;
    private EventStatus status;

    public Event(String name) {
        this.name = name;
        this.constantName = Utils.normalizeString(name);
        this.setStatus(EventStatus.STOP);
    }

}

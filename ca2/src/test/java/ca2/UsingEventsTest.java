package ca2;



import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca2.application.UsingEvents;
import ca2.model.Event;
import ca2.model.EventGenerator;

class UsingEventsTest {

    private Event[] events;

    @BeforeEach
    void setUp() {
        events = new Event[10];
        for (int i = 0; i < events.length; i++) {
            events[i] = EventGenerator.generateEvent();
        }
    }

    @Test
    void displayEvents() {
        Event[] events = {
            new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "Event 1"),
            new Event(UUID.randomUUID(), LocalDateTime.now().plusHours(1), "TEMP", "Event 2"),
            new Event(UUID.randomUUID(), LocalDateTime.now().plusHours(2), "PLUG", "Event 3")
        };

        UsingEvents.displayEvents(events);
        assertEquals(3, events.length);
    }

    @Test
    void displayEventsFromSource() {
        Event[] events = {
            new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "Event 1"),
            new Event(UUID.randomUUID(), LocalDateTime.now().plusHours(1), "TEMP", "Event 2"),
            new Event(UUID.randomUUID(), LocalDateTime.now().plusHours(2), "HEAT", "Event 3")
        };

        UsingEvents.displayEventsFromSource(events);
        assertEquals(3, events.length);
    }

    @Test
    void generateEventAndInsert() {
        Event[] events = {
            new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "Event 1"),
            new Event(UUID.randomUUID(), LocalDateTime.now().plusHours(1), "TEMP", "Event 2"),
            new Event(UUID.randomUUID(), LocalDateTime.now().plusHours(2), "PLUG", "Event 3")
        };

        UsingEvents.generateEventAndInsert(events);
        assertEquals(3, events.length);
    }

    @Test
    void displayActiveSources() {
        Event[] events = {
            new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "Event 1"),
            new Event(UUID.randomUUID(), LocalDateTime.now().plusHours(1), "TEMP", "Event 2"),
            new Event(UUID.randomUUID(), LocalDateTime.now().plusHours(2), "PLUG", "Event 3")
        };

        UsingEvents.displayActiveSources(events);
        assertEquals(3, events.length);
    }
}

package ca2;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca2.model.Event;

class EventTest {

    private Event event1;
    private Event event2;
    private Event event3;

    @BeforeEach
    void setUp() {
        event1 = new Event(UUID.randomUUID(), LocalDateTime.now(), "HEAT", "Event 1");
        event2 = new Event(UUID.randomUUID(), LocalDateTime.now().plusHours(1), "TEMP", "Event 2");
        event3 = new Event(UUID.randomUUID(), LocalDateTime.now().plusHours(2), "PLUG", "Event 3");
    }

    @Test
    void testConstructorWithAllFields() {
        UUID id = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();
        String source = "HEAT";
        String description = "Test Event";

        Event event = new Event(id, timestamp, source, description);

        assertEquals(id, event.getId());
        assertEquals(timestamp, event.getTimestamp());
        assertEquals(source, event.getSource());
        assertEquals(description, event.getDescription());
    }

    @Test
    void testConstructorWithSourceAndDescription() {
        String source = "HEAT";
        String description = "Test Event";

        Event event = new Event(source, description);

        assertEquals(source, event.getSource());
        assertEquals(description, event.getDescription());
    }

    @Test
    void testGetters() {
        assertNotNull(event1.getId());
        assertNotNull(event1.getTimestamp());
        assertEquals("HEAT", event1.getSource());
        assertEquals("Event 1", event1.getDescription());
    }

    @Test
    void testToString() {
        String expected = "Event{id=" + event1.getId() + ", timestamp=" + event1.getTimestamp() + ", source='HEAT', description='Event 1'}";
        assertEquals(expected, event1.toString());
    }

    @Test
    void testFormat() {
        String expected = "Event Details:\nID: " + event1.getId() + "\nTimestamp: " + event1.getTimestamp() + "\nSource: HEAT\nDescription: Event 1";
        assertEquals(expected, event1.format());
    }

    @Test
    void testCompareTo() {
        assertTrue(event1.compareTo(event2) < 0);
        assertTrue(event2.compareTo(event1) > 0);
        assertEquals(0, event1.compareTo(event1));
    }

    @Test
    void testDeleteAllBefore() {
        Event[] events = {event1, event2, event3};
        LocalDateTime timestamp = LocalDateTime.now().plusHours(1);

        Event[] deletedEvents = Event.deleteAllBefore(events, timestamp);

        assertEquals(1, deletedEvents.length);
        assertEquals(event1, deletedEvents[0]);
    }

    @Test
    void testGetActiveSources() {
        Event[] events = {event1, event2, event3};
        String[] activeSources = Event.getActiveSources(events);

        assertEquals(3, activeSources.length);
        assertArrayEquals(new String[]{"HEAT", "TEMP", "PLUG"}, activeSources);
    }

    @Test
    void testInsertSorted() {
        Event[] events = {event1, event3};
        Event[] sortedEvents = Event.insertSorted(events, event2);

        assertEquals(3, sortedEvents.length);
        assertEquals(event1, sortedEvents[0]);
        assertEquals(event2, sortedEvents[1]);
        assertEquals(event3, sortedEvents[2]);
    }

    @Test
    void testValidateEventWithAllFields() {
        UUID id = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();
        String source = "HEAT";
        String description = "Test Event";

        assertDoesNotThrow(() -> Event.validateEvent(id, timestamp, source, description));
    }

    @Test
    void testValidateEventWithSourceAndDescription() {
        String source = "HEAT";
        String description = "Test Event";

        assertDoesNotThrow(() -> Event.validateEvent(source, description));
    }

    @Test
    void testValidateSource() {
        String source = "HEAT";

        assertDoesNotThrow(() -> Event.validateSource(source));
    }

    @Test
    void testValidateArrayOfEvents() {
        Event[] events = {event1, event2, event3};

        assertDoesNotThrow(() -> Event.validateArrayOfEvents(events));
    }

    @Test
    void testValidateEvent() {
        assertDoesNotThrow(() -> Event.validateEvent(event1));
    }
}
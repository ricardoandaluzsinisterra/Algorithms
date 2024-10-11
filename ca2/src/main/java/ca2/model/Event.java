package ca2.model;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents an event with a unique ID, timestamp, source, and description.
 */
public class Event implements Comparable<Event> {
    public final static String[] VALID_SOURCES = {"HEAT", "TEMP", "PLUG", "MOTION", "CAMERA"};
    private UUID id;
    private LocalDateTime timestamp;
    private String source;
    private String description;

    /**
     * Constructs an Event with the specified ID, timestamp, source, and description.
     *
     * @param id          the unique identifier of the event
     * @param timestamp   the timestamp of the event
     * @param source      the source of the event
     * @param description the description of the event
     */
    public Event(UUID id, LocalDateTime timestamp, String source, String description) {
        validateEvent(id, timestamp, source, description);
        this.id = id;
        this.timestamp = timestamp;
        this.source = source;
        this.description = description;
    }

    /**
     * Constructs an Event with the specified source and description.
     *
     * @param source      the source of the event
     * @param description the description of the event
     */
    public Event(String source, String description) {
        validateEvent(source, description);
        this.source = source;
        this.description = description;
    }

    /**
     * Returns the unique identifier of the event.
     *
     * @return the unique identifier of the event
     */
    public UUID getId() {
        return id;
    }

    /**
     * Returns the timestamp of the event.
     *
     * @return the timestamp of the event
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Returns the source of the event.
     *
     * @return the source of the event
     */
    public String getSource() {
        return source;
    }

    /**
     * Returns the description of the event.
     *
     * @return the description of the event
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return a string representation of the event
     */
    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", source='" + source + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    /**
     * Returns a formatted string representation of the event.
     *
     * @return a formatted string representation of the event
     */
    public String format() {
        return "Event Details:\n" +
                "ID: " + id + "\n" +
                "Timestamp: " + timestamp + "\n" +
                "Source: " + source + "\n" +
                "Description: " + description;
    }

    /**
     * Compares this event to another event based on their timestamps.
     *
     * @param other the other event to compare to
     * @return a negative integer, zero, or a positive integer as this event is less than, equal to, or greater than the specified event
     */
    @Override
    public int compareTo(Event other) {
        return this.timestamp.compareTo(other.timestamp);
    }

    /**
     * Deletes all events before the specified timestamp and returns an array of the deleted events.
     *
     * @param eventArray           the array of events
     * @param deleteBeforeTimestamp the timestamp before which events should be deleted
     * @return an array of the deleted events
     */
    public static Event[] deleteAllBefore(Event[] eventArray, LocalDateTime deleteBeforeTimestamp) {
        validateArrayOfEvents(eventArray);
        int index = 0;
        for (int i = 0; i < eventArray.length; i++) {
            if (!eventArray[i].getTimestamp().isBefore(deleteBeforeTimestamp)) {
                index = i;
                break;
            }
        }
        Event[] deletedEvents = new Event[index];
        for (int i = 0; i < index; i++) {
            deletedEvents[i] = eventArray[i];
        }
        return deletedEvents;
    }

    /**
     * Returns an array of active sources from the given array of events.
     *
     * @param eventArray the array of events
     * @return an array of active sources
     */
    public static String[] getActiveSources(Event[] eventArray) {
        validateArrayOfEvents(eventArray);
        // Create a temporary array tempSources to store unique sources, and initialize 
        // a counter count to keep track of the number of unique sources.
        String[] tempSources = new String[eventArray.length];
        int count = 0;
        // For each event in the array get the source of the event and initialize the found boolean.
        for (Event event : eventArray) {
            String source = event.getSource();
            boolean found = false;
            // For loop that checks if the variable was already found.
            for (int i = 0; i < count; i++) {
                if (tempSources[i].equals(source)) {
                    found = true;
                    break;
                }
            }
            // If, after coming out of the loop, the boolean is set to false, 
            // then add the source at index count, and increment the latter.
            if (!found) {
                tempSources[count] = source;
                count++;
            }
        }

        String[] activeSources = new String[count];
        for (int i = 0; i < count; i++) {
            activeSources[i] = tempSources[i];
        }
        return activeSources;
    }

    /**
     * Inserts an event into a sorted array of events and returns the new sorted array.
     *
     * @param eventArray the array of events
     * @param event      the event to be inserted
     * @return the new sorted array of events
     */
    public static Event[] insertSorted(Event[] eventArray, Event event) {
        validateArrayOfEvents(eventArray);
        validateEvent(event);
        int index = 0;
        while (index < eventArray.length && eventArray[index].compareTo(event) < 0) {
            index++;
        }
        Event[] result = new Event[eventArray.length + 1];
        for (int i = 0; i < index; i++) {
            result[i] = eventArray[i];
        }

        result[index] = event;

        for (int i = index; i < eventArray.length; i++) {
            result[i + 1] = eventArray[i];
        }

        return result;
    }

    /**
     * Validates the event with the specified ID, timestamp, source, and description.
     *
     * @param id          the unique identifier of the event
     * @param timestamp   the timestamp of the event
     * @param source      the source of the event
     * @param description the description of the event
     */
    public static void validateEvent(UUID id, LocalDateTime timestamp, String source, String description) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        if (timestamp == null) {
            throw new IllegalArgumentException("timestamp cannot be null");
        }

        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }

        for (String validSource : VALID_SOURCES) {
            if (validSource.equals(source)) {
                return;
            }
        }
        throw new IllegalArgumentException("Invalid source:" + source);
    }

    /**
     * Validates the event with the specified source and description.
     *
     * @param source      the source of the event
     * @param description the description of the event
     */
    public static void validateEvent(String source, String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }

        validateSource(source);
    }

    /**
     * Validates the specified source.
     *
     * @param source the source to be validated
     */
    public static void validateSource(String source) {
        for (String validSource : VALID_SOURCES) {
            if (validSource.equals(source)) {
                return;
            }
        }
        throw new IllegalArgumentException("Invalid source:" + source);
    }

    /**
     * Validates the specified array of events.
     *
     * @param eventArray the array of events to be validated
     */
    public static void validateArrayOfEvents(Event[] eventArray) {
        if (eventArray == null || eventArray.length == 0) {
            throw new IllegalArgumentException("Event array cannot be null nor empty.");
        }
        for (Event event : eventArray) {
            if (event == null) {
                throw new IllegalArgumentException("Event array cannot contain null elements.");
            }
        }
    }

    /**
     * Validates the specified event.
     *
     * @param event the event to be validated
     */
    public static void validateEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }
    }
}
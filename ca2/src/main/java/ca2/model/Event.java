package ca2.model;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Event implements Comparable<Event>{
    public final static String [] VALID_SOURCES = {"HEAT", "TEMP", "PLUG", "MOTION", "CAMERA"};
    private UUID id;
    private LocalDateTime timestamp;
    private String source;
    private String description;

    public Event(UUID id, LocalDateTime timestamp, String source, String description){
        validateEvent(id, timestamp, source, description);
        this.id = id;
        this.timestamp = timestamp;
        this.source = source;
        this.description = description;
    }

    public Event(String source, String description){
        validateEvent(source, description);
        this.source = source;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getSource() {
        return source;
    }

    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", source='" + source + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String format() {
        return "Event Details:\n" +
                "ID: " + id + "\n" +
                "Timestamp: " + timestamp + "\n" +
                "Source: " + source + "\n" +
                "Description: " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, source, description);
    }

    @Override
    public int compareTo(Event other){
        return this.timestamp.compareTo(other.timestamp);
    }

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

    public static String[] getActiveSources(Event[] eventArray){
        validateArrayOfEvents(eventArray);
        //Create a temporary array tempSources to store unique sources, and initialize 
        //a counter count to keep track of the number of unique sources.
        String[] tempSources = new String[eventArray.length];
        int count = 0;
        //For each event in the array get the source of the event and initialize the found boolean.
        for (Event event : eventArray){
            String source = event.getSource();
            boolean found = false;
            //For loop that checks if the variable was already found.
            for (int i = 0; i < count; i++){
                if (tempSources[i].equals(source)){
                    found = true;
                    break;
                }
            }
            //If, after coming out of the loop, the boolean is set to false, 
            //then add the source at index count, and increment the latter.
            if (!found){
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

    private static void validateEvent(UUID id, LocalDateTime timestamp, String source, String description){
        if (id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }

        if (timestamp == null){
            throw new IllegalArgumentException("timestamp cannot be null");
        }

        if (description == null){
            throw new IllegalArgumentException("Description cannot be null");
        }

        for (String validSource : VALID_SOURCES) {
            if (validSource.equals(source)) {
                return;
            }
        }
        throw new IllegalArgumentException("Invalid source:" + source);
    }
    
    private static void validateEvent(String source, String description){
        if (description == null){
            throw new IllegalArgumentException("Description cannot be null");
        }

        validateSource(source);
    }

    public static void validateSource(String source){
        for (String validSource : VALID_SOURCES) {
            if (validSource.equals(source)) {
                return;
            }
        }
        throw new IllegalArgumentException("Invalid source:" + source);
    }

    private static void validateArrayOfEvents(Event[] eventArray){
        if (eventArray == null || eventArray.length == 0){
            throw new IllegalArgumentException("Event array cannot be null nor empty.");
        }
        for (Event event : eventArray) {
            if (event == null) {
                throw new IllegalArgumentException("Event array cannot contain null elements.");
            }
        }
    }

    private static void validateEvent(Event event){
        if(event == null){
            throw new IllegalArgumentException("Event cannot be null");
        }
    }
}


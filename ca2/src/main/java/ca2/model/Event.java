package ca2.model;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Objects;

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

    private void validateEvent(UUID id, LocalDateTime timestamp, String source, String description){
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
    
    private void validateEvent(String source, String description){
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

    
    
}


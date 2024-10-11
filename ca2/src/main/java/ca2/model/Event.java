package ca2.model;
import java.time.LocalDateTime;
import java.util.UUID;

public class Event {
    public final static String[] VALID_SOURCES = {"source"};
    private UUID id;
    private LocalDateTime timestamp;
    private String source;
    private String description;

    public Event(UUID id, LocalDateTime timestamp, String source, String description){
        
        
    }
}


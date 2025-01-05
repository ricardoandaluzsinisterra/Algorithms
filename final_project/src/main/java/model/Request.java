package model;

import java.util.Date;
import java.util.Objects;

public class Request implements Comparable<Request> {
    public final static String[] VALID_REQUESTS = {"GET", "POST", "PUT", "DELETE"};
    private final String requestID;
    private final String userID;
    private final Date timestamp;
    private final String requestType;


    public Request(String requestId, String userId, Date timestamp, String requestType){
        requestValidation(requestId, userId, timestamp, requestType);
        this.requestID = requestId;
        this.userID = userId;
        this.timestamp = timestamp;
        this.requestType = requestType;
    }

    public String getRequestID() {
        return this.requestID;
    }

    public String getUserID() {
        return this.userID;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public String getRequestType() {
        return this.requestType;
    }

    @Override
    public String toString() {
        return "Event{" +
                "requestId=" + requestID +
                ", userId=" + userID +
                ", timestamp='" + timestamp + '\'' +
                ", requestType='" + requestType + '\'' +
                '}';
    }

    public String format() {
        return "\nEvent Details:\n" +
                "Request ID: " + requestID + "\n" +
                "User ID: " + userID + "\n" +
                "Timestamp: " + timestamp + "\n" +
                "Request Type: " + requestType;
    }

    @Override
    public boolean equals(Object other){
        if (this == other){
            return true;
        }

        if (other == null || getClass() != other.getClass()){
            return false;
        }

        Request request = (Request) other;
        return requestID.equals(request.requestID);
    }

    @Override
    public int hashCode(){
        return Objects.hash(requestID);
    }


    @Override
    public int compareTo(Request other) {
        return other.timestamp.compareTo(this.timestamp);
    }

    private static void requestValidation(String requestId, String userId, Date timestamp, String requestType){
        if (requestId == null) {
            throw new IllegalArgumentException("Request id cannot be null");
        }

        if (userId == null) {
            throw new IllegalArgumentException("User id cannot be null");
        }

        if (timestamp == null) {
            throw new IllegalArgumentException("Timestamp cannot be null");
        }

        for (String validRequest : VALID_REQUESTS) {
            if (validRequest.equals(requestType)) {
                return;
            }
        }
        throw new IllegalArgumentException("Invalid source:" + requestType);
    }
}

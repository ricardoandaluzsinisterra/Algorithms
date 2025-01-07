package model;

import java.util.Date;
import java.util.Objects;

/**
 * Represents a request made by a user.
 */
public class Request implements Comparable<Request> {
    public final static String[] VALID_REQUESTS = {"GET", "POST", "PUT", "DELETE"};
    private final String requestID;
    private final String userID;
    private final Date timestamp;
    private final String requestType;

    /**
     * Constructs a new Request.
     *
     * @param requestId   the ID of the request
     * @param userId      the ID of the user making the request
     * @param timestamp   the timestamp of the request
     * @param requestType the type of the request
     * @throws IllegalArgumentException if any of the parameters are invalid
     */
    public Request(String requestId, String userId, Date timestamp, String requestType) {
        requestValidation(requestId, userId, timestamp, requestType);
        this.requestID = requestId;
        this.userID = userId;
        this.timestamp = timestamp;
        this.requestType = requestType;
    }

    /**
     * Returns the ID of the request.
     *
     * @return the ID of the request
     */
    public String getRequestID() {
        return this.requestID;
    }

    /**
     * Returns the ID of the user making the request.
     *
     * @return the ID of the user making the request
     */
    public String getUserID() {
        return this.userID;
    }

    /**
     * Returns the timestamp of the request.
     *
     * @return the timestamp of the request
     */
    public Date getTimestamp() {
        return this.timestamp;
    }

    /**
     * Returns the type of the request.
     *
     * @return the type of the request
     */
    public String getRequestType() {
        return this.requestType;
    }

    /**
     * Returns a string representation of the request.
     *
     * @return a string representation of the request
     */
    @Override
    public String toString() {
        return "Event{" +
                "requestId=" + requestID +
                ", userId=" + userID +
                ", timestamp='" + timestamp + '\'' +
                ", requestType='" + requestType + '\'' +
                '}';
    }

    /**
     * Returns a formatted string representation of the request.
     *
     * @return a formatted string representation of the request
     */
    public String format() {
        return "\nEvent Details:\n" +
                "Request ID: " + requestID + "\n" +
                "User ID: " + userID + "\n" +
                "Timestamp: " + timestamp + "\n" +
                "Request Type: " + requestType;
    }

    /**
     * Compares this request to another request based on their timestamps.
     *
     * @param other the other request to compare to
     * @return a negative integer, zero, or a positive integer as this request is less than, equal to, or greater than the specified request
     */
    @Override
    public int compareTo(Request other) {
        return other.timestamp.compareTo(this.timestamp);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param other the reference object with which to compare
     * @return true if this object is the same as the other argument; false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Request request = (Request) other;
        return requestID.equals(request.requestID);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(requestID);
    }

    /**
     * Validates the parameters of the request.
     *
     * @param requestId   the ID of the request
     * @param userId      the ID of the user making the request
     * @param timestamp   the timestamp of the request
     * @param requestType the type of the request
     * @throws IllegalArgumentException if any of the parameters are invalid
     */
    private static void requestValidation(String requestId, String userId, Date timestamp, String requestType) {
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
        throw new IllegalArgumentException("Invalid source: " + requestType);
    }
}
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.application;
import model.Request;
import utils.DataUtilities;
import utils.HashMap;

public class ApplicationTest {
    private LinkedList<Request> requests;
    private LinkedList<Request> requests2;
    private HashMap<String, Request> userMap;
    private HashMap<String, Request> typeMap;
    private SimpleDateFormat dateFormat;

    @BeforeEach
    public void setUp() throws Exception {
        requests = DataUtilities.fileToArray("src/main/resources/dataset1.txt");
        requests2 = DataUtilities.fileToArray("src/main/resources/dataset2.txt");
        userMap = DataUtilities.linkedToMap(requests, "userId");
        typeMap = DataUtilities.linkedToMap(requests, "requestType");
        dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    }

    @Test
    public void testDisplayRequests() {
        // Assuming displayRequests prints to console, you might want to capture the output
        // Here we just call the method to ensure it runs without exceptions
        application.displayRequests(requests);
    }

    @Test
    public void testUserWithHighestNumberOfRequestsEarliest() {
        application.userWithHighestNumberOfRequests(userMap, "earliest");
    }

    @Test
    public void testUserWithHighestNumberOfRequestsRecent() {
        application.userWithHighestNumberOfRequests(userMap, "recent");
    }

    @Test
    public void testFilterAndDisplayExistingUser() {
        application.filterAndDisplay(userMap, "UserB");
    }

    @Test
    public void testFilterAndDisplayNonExistentUser() {
        application.filterAndDisplay(userMap, "NonExistentUser");
    }

    @Test
    public void testIdentifyRequestTypeGET() {
        application.identifyRequestType(typeMap, "GET");
    }

    @Test
    public void testIdentifyRequestTypePOST() {
        application.identifyRequestType(typeMap, "POST");
    }

    @Test
    public void testIdentifyRequestsWithinTimeFrame() throws Exception {
        Date date1 = dateFormat.parse("2024-12-10T08:05:00");
        Date date2 = dateFormat.parse("2024-12-10T08:40:00");
        application.identifyRequestsWithinTimeFrame(requests, date1, date2);
    }

    @Test
    public void testIdentifyRequestsWithinTimeFrameNoMatches() throws Exception {
        Date date1 = dateFormat.parse("2023-01-01T00:00:00");
        Date date2 = dateFormat.parse("2023-01-01T01:00:00");
        application.identifyRequestsWithinTimeFrame(requests, date1, date2);
    }

    @Test
    public void testUserWithLongestActiveSpan() {
        application.userWithLongestActiveSpan(typeMap);
    }

    @Test
    public void testDeduplication() {
        LinkedList<Request> deduplicated = application.deduplication(requests, requests2);
        assertNotNull(deduplicated);
        // Additional assertions can be added based on expected results
    }

    @Test
    public void testDeduplicationNoDuplicates() {
        LinkedList<Request> uniqueRequests = new LinkedList<>(requests2);
        uniqueRequests.removeAll(requests);
        LinkedList<Request> deduplicated = application.deduplication(requests, uniqueRequests);
        assertEquals(uniqueRequests.size(), deduplicated.size());
    }

    @Test
    public void testDeduplicationAllDuplicates() {
        LinkedList<Request> deduplicated = application.deduplication(requests, requests);
        assertEquals(0, deduplicated.size());
    }
}
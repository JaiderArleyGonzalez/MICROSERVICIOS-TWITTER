package edu.arep.stream;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.arep.Mongo.MongoService;
import org.bson.Document;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StreamServiceTest {

    @Test
    void testGetStream() {
        // Given
        List<Document> mockedMessages = new ArrayList<>();
        mockedMessages.add(new Document("id", 1)
                .append("content", "Message 1"));
        mockedMessages.add(new Document("id", 2)
                .append("content", "Message 2"));

        // When
        StreamService streamService = new StreamService();
        setMockMongoService(streamService, mockedMessages);
        String streamJson = streamService.getStream();

        // Then
        String expectedJson = "{\"messages\":[{\"id\":1,\"content\":\"Message 1\"},{\"id\":2,\"content\":\"Message 2\"}]}";
        assertEquals(expectedJson, streamJson);
    }

    private void setMockMongoService(StreamService streamService, List<Document> mockedMessages) {
        streamService.setMongoService(new MockMongoService(mockedMessages));
    }

    private static class MockMongoService extends MongoService {
        private final List<Document> mockedMessages;

        public MockMongoService(List<Document> mockedMessages) {
            this.mockedMessages = mockedMessages;
        }

        @Override
        public List<Document> getAllMessages() {
            return mockedMessages;
        }
    }
}

package edu.arep.stream;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.arep.Mongo.MongoService;
import org.bson.Document;

public class StreamService {
    private static MongoService mongoService = new MongoService();
    private static Gson gson = new Gson();

    public static String getStream() {
        List<Document> allMessages = mongoService.getAllMessages();

        JsonArray messagesArray = new JsonArray();
        for (Document message : allMessages) {
            JsonObject messageObject = new JsonObject();
            for (String key : message.keySet()) {
                Object value = message.get(key);
                if (value instanceof String) {
                    messageObject.addProperty(key, (String) value);
                } else if (value instanceof Number) {
                    messageObject.addProperty(key, (Number) value);
                } else if (value instanceof Boolean) {
                    messageObject.addProperty(key, (Boolean) value);
                } else if (value instanceof org.bson.types.ObjectId) {
                    messageObject.addProperty(key, value.toString());
                } else {
                    messageObject.add(key, gson.toJsonTree(value));
                }
            }
            messagesArray.add(messageObject);
        }

        JsonObject responseObject = new JsonObject();
        responseObject.add("messages", messagesArray);

        return gson.toJson(responseObject);
    }
}
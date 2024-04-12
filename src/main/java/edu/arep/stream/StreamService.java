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
    /**
     * Obtiene todas las transmisiones desde el servicio MongoDB y las devuelve en formato JSON.
     *
     * @return Una cadena JSON que representa todas las transmisiones.
     */
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
    /**
     * Establece el servicio Mongo utilizado para obtener mensajes para la transmisión.
     *
     * @param mongoService El servicio Mongo que se utilizará para obtener mensajes.
     */
    public void setMongoService(MongoService mongoService) {
        this.mongoService = mongoService;
    }
}
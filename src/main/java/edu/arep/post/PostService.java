package edu.arep.post;

import edu.arep.Mongo.MongoService;
import org.bson.Document;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class PostService implements RequestHandler<Object, Void> {

    private static MongoService mongoService = new MongoService();

    @Override
    public Void handleRequest(Object input, Context context) {
        if (input instanceof Document) {
            addPost((Document) input);
        } else if (input instanceof java.util.Map) {
            @SuppressWarnings("unchecked")
            java.util.Map<String, Object> map = (java.util.Map<String, Object>) input;
            Document post = new Document(map);
            addPost(post);
        } else {
            throw new IllegalArgumentException("Unsupported input type: " + input.getClass());
        }
        return null;
    }

    public static void addPost(Document post) {
        mongoService.addPost(post);
    }
}
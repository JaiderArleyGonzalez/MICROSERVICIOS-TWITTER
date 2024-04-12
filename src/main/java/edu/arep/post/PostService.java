package edu.arep.post;
import edu.arep.Mongo.MongoService;
import org.bson.Document;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class PostService implements RequestHandler<Object, Void> {

    private static MongoService mongoService = new MongoService();
    /**
     * Método que maneja la solicitud Lambda para agregar una publicación.
     *
     * @param input   Objeto de entrada recibido por la función Lambda.
     * @param context Contexto de ejecución de la función Lambda.
     * @return null, ya que no se devuelve ningún resultado.
     * @throws IllegalArgumentException si el tipo de entrada no es compatible.
     */
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
    /**
     * Método estático que agrega una publicación al servicio MongoDB.
     *
     * @param post Documento que representa la publicación a agregar.
     */
    public static void addPost(Document post) {
        mongoService.addPost(post);
    }
}
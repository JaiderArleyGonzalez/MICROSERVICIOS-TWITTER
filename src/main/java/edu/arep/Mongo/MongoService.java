package edu.arep.Mongo;
import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;


public class MongoService {

    private static final String URL_DATABASE = "mongodb://ec2-54-82-247-174.compute-1.amazonaws.com:27017/";
    private static final String DATABASE_NAME = "stream";
    private static final String COLLECTION_NAME = "messages";

    private MongoClient mongoClient;
    private MongoCollection<Document> messagesCollection;

    /**
     * Establece la conexión con la base de datos MongoDB.
     * Crea una instancia de MongoClient y recupera la colección "messages" de la base de datos "stream".
     */
    public void connect() {
        this.mongoClient = MongoClients.create(new ConnectionString(URL_DATABASE));
        MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
        this.messagesCollection = database.getCollection(COLLECTION_NAME);
    }

    /**
     * Cierra la conexión con la base de datos MongoDB.
     */
    public void disconnect() {
        this.mongoClient.close();
    }

    /**
     * Inserta un nuevo documento de mensaje en la base de datos.
     * Establece la conexión, crea un nuevo documento con los detalles del mensaje e lo inserta en la colección.
     * Finalmente, cierra la conexión.
     *
     * @param: mensaje El mensaje a insertar.
     */
    public void addPost(Document message) {
        connect();
        messagesCollection.insertOne(message);
        disconnect();
    }

    /**
     * Obtiene todos los mensajes desde la base de datos MongoDB.
     * Establece la conexión, verifica que la colección de mensajes no sea nula,
     * recupera todos los documentos de la colección y los devuelve como una lista.
     * Finalmente, cierra la conexión.
     *
     * @return Una lista de documentos que representan todos los mensajes almacenados en la base de datos.
     */
    public List<Document> getAllMessages() {
        connect();
        if (messagesCollection == null) {
            throw new IllegalStateException("Connection to MongoDB not established");
        }
        List<Document> messages = new ArrayList<>();
        messagesCollection.find().into(messages);
        disconnect();
        return messages;
    }
}
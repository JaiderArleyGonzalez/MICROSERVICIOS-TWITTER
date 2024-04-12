package edu.arep.Mongo;


import java.time.LocalDateTime;

import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import com.mongodb.client.model.Sorts;

import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

/**
 * The MongoService class provides methods to interact with a MongoDB database
 * for storing and retrieving log records.
 */
public class MongoService {

    private static final String URL_DATABASE = "mongodb://ec2-18-207-245-122.compute-1.amazonaws.com:27017/";
    private static final String DATABASE_NAME = "stream";
    private static final String COLLECTION_NAME = "messages";

    private MongoClient mongoClient;
    private MongoCollection<Document> messagesCollection;

    /**
     * Establishes the connection with the MongoDB database.
     * Creates a MongoClient instance and retrieves the "messages" collection from the "logsdb" database.
     */
    public void connect() {
        this.mongoClient = MongoClients.create(new ConnectionString(URL_DATABASE));
        MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
        this.messagesCollection = database.getCollection(COLLECTION_NAME);
    }

    /**
     * Closes the connection with the MongoDB database.
     */
    public void disconnect() {
        this.mongoClient.close();
    }

    /**
     * Inserts a new message document into the database.
     * Establishes the connection, creates a new document with the message details, and inserts it into the collection.
     * Finally, closes the connection.
     *
     * @param message The message to be inserted.
     */
    public void addPost(Document message) {
        connect();
        messagesCollection.insertOne(message);
        disconnect();
    }


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
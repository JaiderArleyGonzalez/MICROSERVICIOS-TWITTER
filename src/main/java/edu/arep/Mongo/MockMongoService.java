package edu.arep.Mongo;

import org.bson.Document;

public class MockMongoService extends MongoService {
    /**
     * Simula la adición de una publicación al sistema.
     * Esta función imprime en la consola el documento de la publicación que se agregaría.
     * No realiza ninguna operación de almacenamiento real en la base de datos.
     *
     * @param post El documento que representa la publicación a agregar.
     */
    @Override
    public void addPost(Document post) {
        System.out.println("Agregando post (simulado): " + post.toJson());
    }
}
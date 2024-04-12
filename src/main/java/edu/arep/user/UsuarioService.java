package edu.arep.user;
import org.bson.Document;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService implements RequestHandler<Object, Void> {

    private static List<JsonObject> usuarios = new ArrayList<>();

    /**
     * Maneja la solicitud Lambda para agregar un usuario.
     *
     * @param input   Objeto de entrada recibido por la función Lambda.
     * @param context Contexto de ejecución de la función Lambda.
     * @return null, ya que no se devuelve ningún resultado.
     * @throws IllegalArgumentException si el tipo de entrada no es compatible.
     */
    @Override
    public Void handleRequest(Object input, Context context) {
        if (input instanceof Document) {
            addUsuario((Document) input);
        } else if (input instanceof java.util.Map) {
            @SuppressWarnings("unchecked")
            java.util.Map<String, Object> map = (java.util.Map<String, Object>) input;
            Document usuario = new Document(map);
            addUsuario(usuario);
        } else {
            throw new IllegalArgumentException("Unsupported input type: " + input.getClass());
        }
        return null;
    }

    /**
     * Agrega un usuario a la lista de usuarios.
     *
     * @param usuario Documento que representa el usuario a agregar.
     */
    private void addUsuario(Document usuario) {
        JsonObject usuarioJson = new JsonObject();
        for (String key : usuario.keySet()) {
            Object value = usuario.get(key);
            if (value instanceof String) {
                usuarioJson.addProperty(key, (String) value);
            } else if (value instanceof Number) {
                usuarioJson.addProperty(key, (Number) value);
            } else if (value instanceof Boolean) {
                usuarioJson.addProperty(key, (Boolean) value);
            } else {
                usuarioJson.add(key, new Gson().toJsonTree(value));
            }
        }
        usuarios.add(usuarioJson);
    }
}
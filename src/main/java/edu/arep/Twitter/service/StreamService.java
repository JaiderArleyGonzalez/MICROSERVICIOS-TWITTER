package edu.arep.Twitter.service;



import java.io.IOException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.arep.Twitter.model.Post;
import edu.arep.Twitter.model.Stream;

public class StreamService implements RequestHandler<Post, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Stream stream = new Stream();

    @Override
    public String handleRequest(Post input, Context context) { 
        try {
            Post post = objectMapper.readValue(objectMapper.writeValueAsString(input), Post.class);
            stream.addPost(post);
            return getAllPostsJson();
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"error\": \"Error al deserializar el JSON de entrada\"}";
        }
    }

    private String getAllPostsJson() {
        try {
            return objectMapper.writeValueAsString(stream.getAllPosts());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{\"error\": \"Error al serializar los posts a JSON\"}";
        }
    }
}
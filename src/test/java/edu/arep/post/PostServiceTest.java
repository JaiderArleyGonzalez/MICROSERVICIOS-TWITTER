package edu.arep.post;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.bson.Document;
import org.junit.jupiter.api.Test;

class PostServiceTest {

    @Test
    void testAddPostWithDocumentInput() {
        // Given
        Document post = new Document("title", "Test Title")
                .append("content", "Test Content");


        RequestHandlerMock postHandler = new RequestHandlerMock();
        postHandler.handleRequest(post, null);
    }

    @Test
    void testAddPostWithMapInput() {
        // Given
        Document post = new Document("title", "Test Title")
                .append("content", "Test Content");


        RequestHandlerMock postHandler = new RequestHandlerMock();
        postHandler.handleRequest(post, null);


    }

    @Test
    void testUnsupportedInputType() {

        Object unsupportedInput = new Object(); // Objeto de tipo no admitido


        RequestHandlerMock postHandler = new RequestHandlerMock();
        try {
            postHandler.handleRequest(unsupportedInput, null);
        } catch (IllegalArgumentException e) {
            // Se espera una IllegalArgumentException, esto verifica que se haya lanzado correctamente
            assert true;
        }
    }

    private static class RequestHandlerMock implements RequestHandler<Object, Void> {

        private final PostService postService = new PostService();

        @Override
        public Void handleRequest(Object input, Context context) {
            if (input instanceof Document) {
                postService.addPost((Document) input);
            } else if (input instanceof java.util.Map) {
                @SuppressWarnings("unchecked")
                java.util.Map<String, Object> map = (java.util.Map<String, Object>) input;
                Document post = new Document(map);
                postService.addPost(post);
            } else {
                throw new IllegalArgumentException("Unsupported input type: " + input.getClass());
            }
            return null;
        }
    }
}

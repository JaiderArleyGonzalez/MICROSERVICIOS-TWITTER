package edu.arep.Twitter.service;

import java.io.IOException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.arep.Twitter.model.Post;
import edu.arep.Twitter.model.Stream;


public class PostService {



    public String handleRequest(Post input, Context context) { 

        return null;
    }
}

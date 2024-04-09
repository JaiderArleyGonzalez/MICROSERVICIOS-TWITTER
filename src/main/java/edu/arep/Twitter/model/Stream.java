package edu.arep.Twitter.model;

import java.util.ArrayDeque;
import java.util.Deque;

public class Stream {
    private Deque<Post> posts;

    public Stream() {
        this.posts = new ArrayDeque<>();
    }

    public void addPost(Post post) {
        this.posts.push(post);
    }

    public Deque<Post> getAllPosts() {
        return this.posts;
    }
}

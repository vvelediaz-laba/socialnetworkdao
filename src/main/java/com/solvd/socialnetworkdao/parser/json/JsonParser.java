package com.solvd.socialnetworkdao.parser.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.solvd.socialnetworkdao.*;
import com.solvd.socialnetworkdao.parser.sax.DateAdapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public static void userToJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        DateAdapter adapter = new DateAdapter();

        // Create instance of User and populate with data
        User user = new User();
        user.setId(1L);
        user.setEmail("bobsmith@example.com");
        user.setPassword("pa$$word");
        user.setRegistrationDate(adapter.unmarshal("2023-06-12"));

        Profile profile = new Profile();
        profile.setId(1L);
        profile.setFullName("Bob Smith");
        profile.setDateOfBirth(adapter.unmarshal("1990-01-01"));
        profile.setGender("Male");
        profile.setBio("Sample bio");

        List<Post> posts = new ArrayList<>();

        Post post = new Post();
        post.setId(1L);
        post.setDateCreated(adapter.unmarshal("2023-06-12"));
        post.setContent("Sample post content");

        List<Comment> comments = new ArrayList<>();

        Comment comment = new Comment();
        comment.setId(1L);
        comment.setContent("Sample comment");
        comment.setDateCreated(adapter.unmarshal("2023-06-12"));

        comments.add(comment);
        post.setComments(comments);

        posts.add(post);
        profile.setPosts(posts);

        List<Message> messages = new ArrayList<>();

        Message message = new Message();
        message.setId(1L);
        message.setContent("Hello! How are you?");
        message.setDateSent(adapter.unmarshal("2023-06-12"));

        messages.add(message);
        profile.setOutgoingMessages(messages);

        user.setProfile(profile);

        mapper.writeValue(new File("src/main/resources/output.json"), user);
    }

    public static User jsonToUser() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        return mapper.readValue(new File("src/main/resources/user.json"), User.class);
    }
}

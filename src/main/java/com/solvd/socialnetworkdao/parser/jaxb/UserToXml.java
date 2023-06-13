package com.solvd.socialnetworkdao.parser.jaxb;

import com.solvd.socialnetworkdao.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class UserToXml {
    private static final Logger logger = LogManager.getLogger(UserToXml.class.getName());

    public static void main(String[] args) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Create instance of User and populate with data
            User user = new User();
            user.setId(1L);
            user.setEmail("bobsmith@example.com");
            user.setPassword("pa$$word");
            user.setRegistrationDate(Date.valueOf("2023-06-12"));

            Profile receiver = new Profile();
            receiver.setId(2L);

            Profile profile = new Profile();
            profile.setId(1L);
            profile.setFullName("Bob Smith");
            profile.setDateOfBirth(Date.valueOf("1990-01-01"));
            profile.setGender("Male");
            profile.setBio("Sample bio");

            List<Post> posts = new ArrayList<>();

            Post post = new Post();
            post.setId(1L);
            post.setDateCreated(Date.valueOf("2023-06-12 12:00:00"));
            post.setContent("Sample post content");

            List<Comment> comments = new ArrayList<>();

            Comment comment = new Comment();
            comment.setId(1L);
            comment.setAuthorProfile(profile);
            comment.setCommentedPost(post);
            comment.setContent("Sample comment");
            comment.setDateCreated(Date.valueOf("2023-06-12 12:30:00"));

            comments.add(comment);
            post.setComments(comments);

            posts.add(post);
            profile.setPosts(posts);

            List<Message> messages = new ArrayList<>();

            Message message = new Message();
            message.setId(1L);
            message.setContent("Hello! How are you?");
            message.setDateSent(Date.valueOf("2023-06-12 14:30:00"));
            message.setReceiver(receiver);

            messages.add(message);
            profile.setMessages(messages);

            user.setProfile(profile);

            File outputFile = new File("src/main/resources/output.xml");
            marshaller.marshal(user, outputFile);

            logger.info("XML file generated successfully.");
        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
    }
}


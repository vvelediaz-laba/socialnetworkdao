package com.solvd.socialnetworkdao.parser.jaxb;

import com.solvd.socialnetworkdao.*;
import com.solvd.socialnetworkdao.parser.sax.DateAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserToXml {
    private static final Logger logger = LogManager.getLogger(UserToXml.class.getName());
    private static final DateAdapter adapter = new DateAdapter();

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
            post.setDateCreated(adapter.unmarshal("2023-06-12 12:00:00"));
            post.setContent("Sample post content");

            List<Comment> comments = new ArrayList<>();

            Comment comment = new Comment();
            comment.setId(1L);
            comment.setContent("Sample comment");
            comment.setDateCreated(adapter.unmarshal("2023-06-12 12:30:00"));

            comments.add(comment);
            post.setComments(comments);

            posts.add(post);
            profile.setPosts(posts);

            List<Message> messages = new ArrayList<>();

            Message message = new Message();
            message.setId(1L);
            message.setContent("Hello! How are you?");
            message.setDateSent(adapter.unmarshal("2023-06-12 14:30:00"));

            messages.add(message);
            profile.setOutgoingMessages(messages);

            user.setProfile(profile);

            File outputFile = new File("src/main/resources/output.xml");
            marshaller.marshal(user, outputFile);

            logger.info("XML file generated successfully.");
        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
    }
}


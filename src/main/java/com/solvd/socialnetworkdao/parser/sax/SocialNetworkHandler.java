package com.solvd.socialnetworkdao.parser.sax;

import com.solvd.socialnetworkdao.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SocialNetworkHandler extends DefaultHandler {
    private static final DateAdapter adapter = new DateAdapter();
    private String currentValue;
    private User user;
    private Profile profile;
    private Post post;
    private Comment comment;
    private Message message;
    private boolean isUser, isProfile, isPost, isComment, isMessage;

    @Override
    public void startDocument() {
        this.user = new User();
        this.profile = new Profile();
        this.post = new Post();
        this.comment = new Comment();
        this.message = new Message();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentValue = "";
        switch(qName){
            case "user":
                isUser = true;
                break;
            case "profile":
                isUser = false;
                isProfile = true;
                break;
            case "post":
                isProfile = false;
                isPost = true;
                break;
            case "comment":
                isPost = false;
                isComment = true;
                break;
            case "message":
                isComment = false;
                isMessage = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        currentValue = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "id":
                if(isUser) user.setId(Long.valueOf(currentValue));
                if(isProfile) user.setId(Long.valueOf(currentValue));
                if(isPost) post.setId(Long.valueOf(currentValue));
                if(isComment) comment.setId(Long.valueOf(currentValue));
                if(isMessage) comment.setId(Long.valueOf(currentValue));
                break;
            case "email":
                user.setEmail(currentValue);
                break;
            case "password":
                user.setPassword(currentValue);
                break;
            case "registration_date":
                user.setRegistrationDate(adapter.unmarshal(currentValue));
                break;
            case "full_name":
                profile.setFullName(currentValue);
                break;
            case "date_of_birth":
                profile.setDateOfBirth(adapter.unmarshal(currentValue));
                break;
            case "gender":
                profile.setGender(currentValue);
                break;
            case "bio":
                profile.setBio(currentValue);
                break;
            case "date_created":
                if(isPost) post.setDateCreated(adapter.unmarshal(currentValue));
                if(isComment) comment.setDateCreated(adapter.unmarshal(currentValue));
                break;
            case "content":
                if(isPost) post.setContent(currentValue);
                if(isComment) comment.setContent(currentValue);
                if(isMessage) message.setContent(currentValue);
                break;
            case "date_sent":
                message.setDateSent(adapter.unmarshal(currentValue));
                break;
        }
    }

    @Override
    public void endDocument() {
        List<Post> posts = new ArrayList<>();
        List<Message> messages = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        posts.add(post);
        messages.add(message);

        post.setComments(comments);
        profile.setOutgoingMessages(messages);
        profile.setPosts(posts);

        user.setProfile(profile);
        profile.setPosts(posts);
    }

    public User getUser() {
        return user;
    }
}

package com.solvd.socialnetworkdao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.sql.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class Message {
    @XmlElement
    private Long id;
    @XmlElement
    private String content;
    @XmlElement(name = "receiver_profile")
    private Profile receiver;
    @XmlElement(name = "sender_profile")
    private Profile sender;
    @XmlElement(name = "date_sent")
    private Date dateSent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Profile getReceiver() {
        return receiver;
    }

    public void setReceiver(Profile receiver) {
        this.receiver = receiver;
    }

    public Profile getSender() {
        return sender;
    }

    public void setSender(Profile sender) {
        this.sender = sender;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }
}

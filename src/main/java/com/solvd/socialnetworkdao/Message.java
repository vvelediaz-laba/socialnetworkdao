package com.solvd.socialnetworkdao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.solvd.socialnetworkdao.parser.json.DateSerializer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.sql.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class Message {
    @JsonProperty
    @XmlElement
    private Long id;
    @JsonProperty
    @XmlElement
    private String content;
    @JsonSerialize(using = DateSerializer.class)
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

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", dateSent=" + dateSent +
                '}';
    }
}

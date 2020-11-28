package brankosaponjic.playlist.model;

import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Document(collection = "PlayList")
public class Song {
    @Id
    private String id;
    private int orderNo;
    @NotEmpty(message = "You must enter the title!")
    @Size(message = "Title must be at least 2 characters long.")
    private String title;
    @Nullable
    private String length;
    @Nullable
    private String author;
    @NotEmpty(message = "You must enter the link!")
    @URL(message = "URL must be valid")
    private String link;
    private static int counter = 0;

    public Song() {
        orderNo=counter++;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
package com.solvd.socialnetworkdao;

import java.sql.Date;
import java.util.List;

public class PhotoAlbum {
    private Long id;
    private String photoAlbumName;
    private Date dateCreated;
    private List<Photo> photos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoAlbumName() {
        return photoAlbumName;
    }

    public void setPhotoAlbumName(String photoAlbumName) {
        this.photoAlbumName = photoAlbumName;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "PhotoAlbum [id=" + id +
                ", photoAlbumName='" + photoAlbumName + "'" +
                ", dateCreated=" + dateCreated +
                "]";
    }
}

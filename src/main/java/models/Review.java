package models;

import java.util.Objects;

public class Review {
    private int id;
    private String title;
    private int rating;
    private String content;
    private String userName;
    private int specialId;

    public Review(String title, int rating, String content, String userName, int specialId) {
        this.title = title;
        this.rating = rating;
        this.content = content;
        this.userName = userName;
        this.specialId = specialId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSpecialId() {
        return specialId;
    }

    public void setSpecialId(int specialId) {
        this.specialId = specialId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return rating == review.rating &&
                specialId == review.specialId &&
                Objects.equals(title, review.title) &&
                Objects.equals(content, review.content) &&
                Objects.equals(userName, review.userName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, rating, content, userName, specialId);
    }
}

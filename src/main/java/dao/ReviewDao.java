package dao;

import models.Review;

import java.util.List;

public interface ReviewDao {
    //create
    void add(Review review);

    //read
    Review findById(int id);
    List<Review> getAll();

    //update
    void update(String title, int rating, String content, String userName, int specialId);

    //delete
    void deleteById(int id);
    void deleteAll();
}

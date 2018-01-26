package dao;

import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oReviewDao implements ReviewDao {

    Sql2o sql2o;

    public Sql2oReviewDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    //create
    public void add(Review review) {
        String sql = "INSERT INTO reviews (title, rating, content, userName, specialId) VALUES (:title, :rating, :content, :userName, :specialId)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(review)
                    .executeUpdate()
                    .getKey();
            review.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    //read
    public Review findById(int id) {
        String sql = "SELECT * FROM reviews WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Review.class);
        }
    }
    public List<Review> getAll() {
        String sql = "SELECT * FROM reviews";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Review.class);
        }
    }

    //update
    public void update(int id, String title, int rating, String content, String userName, int specialId) {
        String sql = "UPDATE reviews SET title = :title, rating = :rating, content = :content, userName = :userName, specialId = :specialId WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("title", title)
                    .addParameter("rating", rating)
                    .addParameter("content", content)
                    .addParameter("userName", userName)
                    .addParameter("specialId", specialId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    //delete
    public void deleteById(int id) {
        String sql = "DELETE FROM reviews WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    public void deleteAll() {
        String sql = "DELETE FROM reviews";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}

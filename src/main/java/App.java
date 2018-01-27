import com.google.gson.Gson;
import dao.Sql2oComedianDao;
import dao.Sql2oReviewDao;
import dao.Sql2oSpecialDao;
import models.Comedian;
import models.Review;
import models.Special;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.post;

public class App {

    public static void main(String[] args) {

        Sql2oComedianDao comedianDao;
        Sql2oSpecialDao specialDao;
        Sql2oReviewDao reviewDao;
        Connection conn;

        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/sus.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        comedianDao = new Sql2oComedianDao(sql2o);
        specialDao = new Sql2oSpecialDao(sql2o);
        reviewDao = new Sql2oReviewDao(sql2o);
        conn = sql2o.open();

        // CREATE
        // "/comedians/new"
        post("/comedians/new", "application/json", (req, res) -> {
            Comedian comedian = gson.fromJson(req.body(), Comedian.class);
            comedianDao.add(comedian);
            res.status(201);
            return gson.toJson(comedian);
        });
        // "comedians/:id/specials/new"
        post("/comedians/:id/specials/new", "application/json", (req, res) -> {
            int comedianId = Integer.parseInt(req.params("id"));
            Special special = gson.fromJson(req.body(), Special.class);
            special.setComedianId(comedianId);
            specialDao.add(special);
            res.status(201);
            return gson.toJson(special);
        });
        // "comedians/:cId/specials/:sId/reviews/new"
        post("/comedians/:cId/specials/:sId/reviews/new", "application/json", (req, res) -> {
            int comedianId = Integer.parseInt(req.params("cId"));
            int specialId = Integer.parseInt(req.params("sId"));
            Review review = gson.fromJson(req.body(), Review.class);
            review.setSpecialId(specialId);
            reviewDao.add(review);
            res.status(201);
            return gson.toJson(review);
        });
        // READ ALL
        // "/comedians"
        get("/comedians", "application/json", (req, res) -> {
            return gson.toJson(comedianDao.getAll());
        });
        // "comedians/:id/specials"
        get("/comedians/:id/specials", "application/json", (request, response) -> {
            int comedianId = Integer.parseInt(request.params("id"));
            Comedian comedian = comedianDao.findById(comedianId);
            List<Special> specials = specialDao.findByComedian(comedianId);
            return gson.toJson(specials);
        });
        // "comedians/:cId/specials/:sId/reviews"
        get("comedians/:cId/specials/:sId/reviews", "application/json", (req, res) -> {
            int comedianId = Integer.parseInt(req.params("cId"));
            int specialId = Integer.parseInt(req.params("sId"));
            Special special = specialDao.findById(specialId);
            List<Review> reviews = reviewDao.findBySpecial(specialId);
            return gson.toJson(reviews);
        });
        // READ ONE
        // "/comedians/:id"
        get("/comedians/:id", "application/json", (req, res) -> {
            int comedianId = Integer.parseInt(req.params("id"));
            Comedian comedian = comedianDao.findById(comedianId);
            return gson.toJson(comedianDao.findById(comedianId));
        });
        // "comedians/:cId/specials/:sId"
        get("/comedians/:cId/specials/:sId", "application/json", (req, res) -> {
            int comedianId = Integer.parseInt(req.params("cId"));
            int specialId = Integer.parseInt(req.params("sId"));
            Special special = specialDao.findById(specialId);
            return gson.toJson(special);
        });
        // "comedians/:cId/specials/:sId/reviews/:rId"
        get("/comedians/:cId/specials/:sId/reviews/:rId", "application/json", (req, res) -> {
            int comedianId = Integer.parseInt(req.params("cId"));
            int specialId = Integer.parseInt(req.params("sId"));
            int reviewId = Integer.parseInt(req.params("rId"));
            Review review = reviewDao.findById(reviewId);
            return gson.toJson(review);
        });
        // UPDATE
        // "/comedians/:id/update"
        post("/comedians/:id/update", "application/json", (req, res) -> {
            int comedianId = Integer.parseInt(req.params("id"));
            Comedian comedian0 = comedianDao.findById(comedianId);
            Comedian comedian = gson.fromJson(req.body(), Comedian.class);
            comedianDao.update(comedianId, comedian.getName());
            res.status(201);
            return gson.toJson(comedian0);
        });
        // "comedians/:cId/specials/:sId/update"
        post("/comedians/:cId/specials/:sId/update", "application/json", (req, res) -> {
            int specialId = Integer.parseInt(req.params("sId"));
            Special special0 = specialDao.findById(specialId);
            Special special = gson.fromJson(req.body(), Special.class);
            specialDao.update(specialId, special.getName(), special.getYear(), special.getComedianId(), special.getCountry(), special.getLanguage(), special.getDescription());
            res.status(201);
            return gson.toJson(special0);
        });
        // "comedians/:cId/specials/:sId/reviews/:rId/update"
        post("/comedians/:cId/specials/:sId/reviews/:rId/update", "application/json", (req, res) -> {
            int reviewId = Integer.parseInt(req.params("rId"));
            Review review0 = reviewDao.findById(reviewId);
            Review review = gson.fromJson(req.body(), Review.class);
            reviewDao.update(reviewId, review.getTitle(), review.getRating(), review.getContent(), review.getUserName(), review.getSpecialId());
            res.status(201);
            return gson.toJson(review0);
        });
        // DELETE
        // "/comedians/:id/delete"
        get("/comedians/:id/delete", "application/json", (req, res) -> {
            int comedianId = Integer.parseInt(req.params("id"));
            comedianDao.deleteById(comedianId);
            return 0;
        });
        // "comedians/:cId/specials/:sId/delete"
        get("/comedians/:cId/specials/:sId/delete", "application/json", (req, res) -> {
            int specialId = Integer.parseInt(req.params("sId"));
            specialDao.deleteById(specialId);
            return 0;
        });
        // "comedians/:cId/specials/:sId/reviews/:rId/delete"
        get("/comedians/:cId/specials/:sId/reviews/:rId/delete", "application/json", (req, res) -> {
            int reviewId = Integer.parseInt(req.params("rId"));
            reviewDao.deleteById(reviewId);
            return 0;
        });
        // "/delete_all"
        get("/delete_all", "application/json", (req, res) -> {
            reviewDao.deleteAll();
            specialDao.deleteAll();
            comedianDao.deleteAll();
            return 0;
        });

        //Filters
        after((req, res) -> {
            res.type("application/json");
        });
    }
}

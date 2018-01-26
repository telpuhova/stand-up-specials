package dao;

import models.Review;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oReviewDaoTest {

    Connection con;
    Sql2oReviewDao reviewDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        reviewDao = new Sql2oReviewDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void add() {
        Review review = new Review("Doug's work", 3,  "okay", "mike", 1);
        reviewDao.add(review);
        assertEquals(1, reviewDao.getAll().size());
        assertEquals(1, review.getId());
    }

    @Test
    public void findById() {
        Review review = new Review("Doug's work", 3,  "okay", "mike", 1);
        reviewDao.add(review);
        Review review2 = new Review("Demetri", 3,  "okay", "mike", 2);
        reviewDao.add(review2);
        assertEquals(review2, reviewDao.findById(review2.getId()));
    }

    @Test
    public void getAll() {
        Review review = new Review("Doug's work", 3,  "okay", "mike", 1);
        reviewDao.add(review);
        Review review2 = new Review("Demetri", 3,  "okay", "mike", 2);
        reviewDao.add(review2);
        assertEquals(2, reviewDao.getAll().size());
    }

    @Test
    public void update() {
        Review review = new Review("Doug's work", 3,  "okay", "mike", 1);
        reviewDao.add(review);
        reviewDao.update(review.getId(), "Stanhope's work", 3,  "okay", "mike", 1);
        assertEquals("Stanhope's work", reviewDao.findById(review.getId()).getTitle());
    }

    @Test
    public void deleteById() {
        Review review = new Review("Doug's work", 3,  "okay", "mike", 1);
        reviewDao.add(review);
        Review review2 = new Review("Demetri", 3,  "okay", "mike", 2);
        reviewDao.add(review2);
        reviewDao.deleteById(review2.getId());
        assertEquals(1, reviewDao.getAll().size());
        assertEquals(review, reviewDao.getAll().get(0));
    }

    @Test
    public void deleteAll() {
        Review review = new Review("Doug's work", 3,  "okay", "mike", 1);
        reviewDao.add(review);
        Review review2 = new Review("Demetri", 3,  "okay", "mike", 2);
        reviewDao.add(review2);
        reviewDao.deleteAll();
        assertEquals(0, reviewDao.getAll().size());
    }

}
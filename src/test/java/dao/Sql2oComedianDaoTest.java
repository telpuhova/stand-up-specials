package dao;

import models.Comedian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oComedianDaoTest {

    Connection con;
    Sql2oComedianDao comedianDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        comedianDao = new Sql2oComedianDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void add() {
        Comedian comedian = new Comedian("Doug Stanhope");
        comedianDao.add(comedian);
        assertEquals(1, comedianDao.getAll().size());
        assertEquals(1, comedian.getId());
    }

    @Test
    public void findById() {
        Comedian comedian = new Comedian("Doug Stanhope");
        comedianDao.add(comedian);
        Comedian comedian2 = new Comedian("Demetri Martin");
        comedianDao.add(comedian2);
        Comedian comedian3 = new Comedian("Bo Burnham");
        comedianDao.add(comedian3);
        assertEquals(comedian2, comedianDao.findById(comedian2.getId()));
    }

    @Test
    public void getAll() {
        Comedian comedian = new Comedian("Doug Stanhope");
        comedianDao.add(comedian);
        Comedian comedian2 = new Comedian("Demetri Martin");
        comedianDao.add(comedian2);
        Comedian comedian3 = new Comedian("Bo Burnham");
        comedianDao.add(comedian3);
        assertEquals(3, comedianDao.getAll().size());
    }

    @Test
    public void update() {
        Comedian comedian = new Comedian("Doug Stanhope");
        comedianDao.add(comedian);
        comedianDao.update(comedian.getId(), "George Carlin");
        assertEquals("George Carlin", comedianDao.findById(comedian.getId()).getName());
    }

    @Test
    public void deleteById() {
        Comedian comedian = new Comedian("Doug Stanhope");
        comedianDao.add(comedian);
        Comedian comedian2 = new Comedian("Demetri Martin");
        comedianDao.add(comedian2);
        comedianDao.deleteById(comedian2.getId());
        assertEquals(1, comedianDao.getAll().size());
        assertEquals(comedian, comedianDao.getAll().get(0));
    }

    @Test
    public void deleteAll() {
        Comedian comedian = new Comedian("Doug Stanhope");
        comedianDao.add(comedian);
        Comedian comedian2 = new Comedian("Demetri Martin");
        comedianDao.add(comedian2);
        Comedian comedian3 = new Comedian("Bo Burnham");
        comedianDao.add(comedian3);
        comedianDao.deleteAll();
        assertEquals(0, comedianDao.getAll().size());
    }
}
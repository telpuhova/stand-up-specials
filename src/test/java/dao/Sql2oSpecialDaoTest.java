package dao;

import models.Special;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oSpecialDaoTest {

    Connection con;
    Sql2oSpecialDao specialDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        specialDao = new Sql2oSpecialDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void add() {
        Special special = new Special("You're all diseased");
        specialDao.add(special);
        assertEquals(1, specialDao.getAll().size());
        assertEquals(1, special.getId());
    }

    @Test
    public void findById() {
        Special special = new Special("You're all diseased");
        specialDao.add(special);
        Special special2 = new Special("If I");
        specialDao.add(special2);
        Special special3 = new Special("What.");
        specialDao.add(special3);
        assertEquals(special2, specialDao.findById(special2.getId()));
    }

    @Test
    public void getAll() {
        Special special = new Special("You're all diseased");
        specialDao.add(special);
        Special special2 = new Special("If I");
        specialDao.add(special2);
        Special special3 = new Special("What.");
        specialDao.add(special3);
        assertEquals(3, specialDao.getAll().size());
    }

    @Test
    public void update() {
        Special special = new Special("You're all diseased");
        specialDao.add(special);
        specialDao.update(special.getId(), "You're all diseased", 1999, 1, "USA", "eng", "djhfkjfdkjnfk");
        assertEquals(1999, specialDao.findById(special.getId()).getYear());
    }

    @Test
    public void deleteById() {
        Special special = new Special("You're all diseased");
        specialDao.add(special);
        Special special2 = new Special("If I");
        specialDao.add(special2);
        specialDao.deleteById(special2.getId());
        assertEquals(1, specialDao.getAll().size());
        assertEquals(special, specialDao.getAll().get(0));
    }

    @Test
    public void deleteAll() {
        Special special = new Special("Doug Stanhope");
        specialDao.add(special);
        Special special2 = new Special("If I");
        specialDao.add(special2);
        Special special3 = new Special("What.");
        specialDao.add(special3);
        specialDao.deleteAll();
        assertEquals(0, specialDao.getAll().size());
    }
}
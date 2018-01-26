package dao;

import models.Comedian;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oComedianDao implements ComedianDao{

    Sql2o sql2o;

    public Sql2oComedianDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    //create
    @Override
    public void add(Comedian comedian) {
        String sql = "INSERT INTO comedians (name, dateOfBirth) VALUES (:name, :dateOfBirth)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(comedian)
                    .executeUpdate()
                    .getKey();
            comedian.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    //read
    @Override
    public Comedian findById(int id) {
        String sql = "SELECT * FROM comedians WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Comedian.class);
        }
    }

    @Override
    public List<Comedian> getAll() {
        String sql = "SELECT * FROM comedians";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Comedian.class);
        }
    }

    //update
    @Override
    public void update(int id, String name) {
        String sql = "UPDATE comedians SET name = :name WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    //delete
    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM comedians WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM comedians";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}

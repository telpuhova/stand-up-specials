package dao;

import models.Special;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oSpecialDao implements SpecialDao{

    Sql2o sql2o;

    public Sql2oSpecialDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    //create
    @Override
    public void add(Special special) {
        String sql = "INSERT INTO specials (name, year, comedianId, country, language, description) VALUES (:name, :year, :comedianId, :country, :language, :description)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(special)
                    .executeUpdate()
                    .getKey();
            special.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    //read
    @Override
    public Special findById(int id) {
        String sql = "SELECT * FROM specials WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Special.class);
        }
    }
    @Override
    public List<Special> getAll() {
        String sql = "SELECT * FROM specials";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Special.class);
        }
    }

    public List<Special> findByComedian(int comedianId){
        String sql = "SELECT * FROM specials WHERE comedianId = :comedianId";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("comedianId", comedianId)
                    .executeAndFetch(Special.class);
        }
    }

    //update
    @Override
    public void update(int id, String name, int year, int comedianId, String country, String language, String description) {
        String sql = "UPDATE specials SET name = :name, year = :year, comedianId = :comedianId, country = :country, language = :language, description = :description WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .addParameter("year", year)
                    .addParameter("comedianId", comedianId)
                    .addParameter("country", country)
                    .addParameter("language", language)
                    .addParameter("description", description)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    //delete
    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM specials WHERE id = :id";
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
        String sql = "DELETE FROM specials";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}

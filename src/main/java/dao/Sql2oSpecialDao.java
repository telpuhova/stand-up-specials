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
    public void add(Special special) {
        String sql = "INSERT INTO specials (name, year, comicId, country, language, description) VALUES (:name, :year, :comicId, :country, :language, :description)";
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
    public Special findById(int id) {
        String sql = "SELECT * FROM specials WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Special.class);
        }
    }
    public List<Special> getAll() {
        String sql = "SELECT * FROM specials";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Special.class);
        }
    }

    //update
    public void update(int id, String name, int year, int comicId, String country, String language, String description) {
        String sql = "UPDATE specials SET name = :name, year = :year, comicId = :comicId, country = :country, language = :language, description = :description WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .addParameter("year", year)
                    .addParameter("comicId", comicId)
                    .addParameter("country", country)
                    .addParameter("language", language)
                    .addParameter("description", description)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    //delete
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

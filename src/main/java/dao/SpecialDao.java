package dao;

import models.Special;

import java.util.List;

public interface SpecialDao {

    //create
    void add(Special special);

    //read
    Special findById(int id);
    List<Special> getAll();

    //update
    void update(int id, String name, int year, int comicId, String country, String language, String description);

    //delete
    void deleteById(int id);
    void deleteAll();
}

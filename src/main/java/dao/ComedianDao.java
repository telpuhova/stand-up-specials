package dao;

import models.Comedian;

import java.util.List;

public interface ComedianDao {

    //create
    void add(Comedian comedian);

    //read
    Comedian findById(int id);
    List<Comedian> getAll();

    //update
    void update(String name);

    //delete
    void deleteById(int id);
    void deleteAll();
}

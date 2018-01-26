package models;

import java.util.Objects;

public class Comedian {
    private int id;
    private String name;
    private String dateOfBirth;

    public Comedian(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comedian comedian = (Comedian) o;
        return Objects.equals(name, comedian.name) &&
                Objects.equals(dateOfBirth, comedian.dateOfBirth);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, dateOfBirth);
    }
}

package models;

import java.util.Objects;

public class Special {
    private int id;
    private String name;
    private int year;
    private int comedianId;
    private String country;
    private String language;
    private String description;

    public Special(String name) {
        this.name = name;
    }

    public Special(String name, int year, int comicId, String country, String language, String description) {
        this.name = name;
        this.year = year;
        this.comedianId = comicId;
        this.country = country;
        this.language = language;
        this.description = description;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getComicId() {
        return comedianId;
    }

    public void setComicId(int comicId) {
        this.comedianId = comicId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Special special = (Special) o;
        return year == special.year &&
                comedianId == special.comedianId &&
                Objects.equals(name, special.name) &&
                Objects.equals(country, special.country) &&
                Objects.equals(language, special.language) &&
                Objects.equals(description, special.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, year, comedianId, country, language, description);
    }
}

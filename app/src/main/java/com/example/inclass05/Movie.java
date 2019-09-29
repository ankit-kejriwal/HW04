package com.example.inclass05;


import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

public class Movie implements Serializable {
    String name;
    String desc;
    String genre;
    Integer rating;
    Integer year;
    URL imdb;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public URL getImdb() {
        return imdb;
    }

    public void setImdb(URL imdb) {
        this.imdb = imdb;
    }

    public Movie(String name, String desc, String genre, int rating, int year, URL imdb) {
        this.name = name;
        this.desc = desc;
        this.genre = genre;
        this.rating = rating;
        this.year = year;
        this.imdb = imdb;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                ", year=" + year +
                ", imdb=" + imdb +
                '}';
    }
}

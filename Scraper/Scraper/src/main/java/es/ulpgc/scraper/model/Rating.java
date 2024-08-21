package es.ulpgc.scraper.model;

import com.google.gson.Gson;

public class Rating {
    public String name;
    public String rating;

    public Rating(String name, String rating){
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return this.name;
    }

    public String getRating() {
        return this.rating;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

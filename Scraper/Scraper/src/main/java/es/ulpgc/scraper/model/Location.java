package es.ulpgc.scraper.model;

import com.google.gson.Gson;

public class Location {
    public String name;
    public String location;


    public Location(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}


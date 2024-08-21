package es.ulpgc.scraper.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class Service {
    public String name;
    public String service;

    public Service(JsonArray name, JsonArray service) {
        this.name = name.toString().replaceAll("[\"\\[\\]]","");
        this.service = service.toString().replaceAll("[\"\\[\\]]","");
    }

    public String getName() {
        return name;
    }

    public String getService() {
        return service;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}


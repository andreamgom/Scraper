package es.ulpgc.scraper.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class Comment {
    public String name;
    public String country;
    public String punctuation;
    public String review;
    public String positive;
    public String negative;
    public String days;


    public Comment(JsonArray name, JsonArray country, JsonArray punctuation, JsonArray review, JsonArray positive, JsonArray negative, JsonArray days) {
        this.name = name.toString().replaceAll("[\"\\[\\]]","");
        this.country = country.toString().replaceAll("[\"\\[\\]]","");
        this.punctuation = punctuation.toString().replaceAll("[\"\\[\\]]","");
        this.review = review.toString().replaceAll("[\"\\u201C\\u201D\\[\\]]","");
        this.positive = positive.toString().replaceAll("[\"\\[\\]]","");
        this.negative = negative.toString().replaceAll("[\"\\[\\]]","");
        this.days = days.toString().replaceAll("[\"\\[\\]]","");
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getPunctuation() {
        return punctuation;
    }

    public String getReview() {
        return review;
    }

    public String getPositive() {
        return positive;
    }

    public String getNegative() {
        return negative;
    }

    public String getDays() {
        return days;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

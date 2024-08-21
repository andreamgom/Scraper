package es.ulpgc.scraper.webservice;

import com.google.gson.JsonArray;
import es.ulpgc.scraper.model.Comment;
import es.ulpgc.scraper.model.Service;
import es.ulpgc.scraper.model.Rating;
import es.ulpgc.scraper.model.Location;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



import java.util.*;

import java.io.IOException;

public class Scraper implements BookingSource {
    private Document getHtmlDocument(String url) {
        try {
            return Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
        } catch (IOException ex) {
            System.out.println("Error al obtener el HTML" + ex.getMessage());
            return null;
        }
    }
    @Override
    public List<Location> getLocations(String url) {
        List<Location> locations = new ArrayList<>();
        Document doc = getHtmlDocument(url);
        if (doc != null) {
            Elements hotel = doc.select("div.wrap-hotelpage-top");
            for (Element elem : hotel) {
                String name = elem.getElementsByClass("d2fee87262 pp-header__title").text();
                String location = elem.getElementsByClass("hp_address_subtitle").text();
                locations.add(new Location(name, location));
            }
        }
        return locations;
    }
    @Override
    public List<Rating> getRatings(String url) {
        List<Rating> assessments = new ArrayList<>();
        Map<String, String> hm = new HashMap<String, String>();
        Document doc = getHtmlDocument(url);
        if (doc != null) {
            Elements ratings = doc.select("div.a1b3f50dcd.b2fe1a41c3.a1f3ecff04.e187349485.d19ba76520");
            for (Element elem : ratings) {
                String name = elem.getElementsByClass("d6d4671780").text();
                String rating = elem.getElementsByClass("ee746850b6 b8eef6afe1").text();
                hm.put(name, rating);
            }
            Set<String> keys = hm.keySet();
            for (String key : keys) {
                String value = hm.get(key);
                assessments.add(new Rating(key, value));
            }
        }
        return assessments;
    }
    @Override
    public List<Service> getServices(String url) {
        List<Service> services = new ArrayList<>();
        Document doc = getHtmlDocument(url);
        if (doc != null) {
            Elements serviceElements = doc.select("div.hotel-facilities-group");
            for (Element elem : serviceElements) {
                JsonArray name = new JsonArray();
                JsonArray serviceArray = new JsonArray();
                name.add(elem.getElementsByClass("bui-title bui-title--strong_1 bui-spacer--medium hotel-facilities-group__title").text());
                Elements serviceList = elem.getElementsByClass("bui-list__description");
                for (Element s : serviceList) {
                    serviceArray.add(s.text());
                }
                services.add(new Service(name, serviceArray));
            }
        }
        return services;
    }
    @Override
    public List<Comment> getComments(String url) {
        List<Comment> comments = new ArrayList<>();
        Document doc = getHtmlDocument(url);
        if (doc != null) {
            Elements commentary = doc.select("li.review_item.clearfix");
            for (Element elem : commentary) {
                JsonArray name = new JsonArray();
                JsonArray country = new JsonArray();

                Elements users = elem.select("div.review_item_reviewer");
                for (Element user : users) {
                    String n = user.getElementsByClass("reviewer_name").text();
                    String c = user.getElementsByClass("reviewer_country").text();
                    name.add(n);
                    country.add(c);
                }
                JsonArray punctuation = new JsonArray();
                JsonArray review = new JsonArray();

                Elements punctuations = elem.select("div.review_item_header_score_container");
                for (Element punctuationArray : punctuations) {
                    String p = punctuationArray.text();
                    punctuation.add(p);
                }
                Elements reviews = elem.select("div.review_item_header_content");
                for (Element r : reviews) {
                    String re = r.text();
                    review.add(re);
                }
                JsonArray positive = new JsonArray();
                JsonArray negative = new JsonArray();
                JsonArray days = new JsonArray();

                Elements annotations = elem.select("div.review_item_review_content");
                for (Element annotation : annotations) {
                    String p = annotation.getElementsByClass("review_pos").text();
                    String n = annotation.getElementsByClass("review_neg").text();
                    String d = annotation.getElementsByClass("review_staydate").text();
                    positive.add(p);
                    negative.add(n);
                    days.add(d);
                }
                comments.add(new Comment(name, country, punctuation, review, positive, negative, days));
            }
        }
        return comments;
    }
}


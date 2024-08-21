package es.ulpgc.scraper.webservice;

import es.ulpgc.scraper.model.Rating;
import es.ulpgc.scraper.model.Comment;
import es.ulpgc.scraper.model.Location;
import es.ulpgc.scraper.model.Service;

import java.util.List;

public interface BookingSource {
        List<Rating> getRatings(String url) throws Exception;
        List<Location> getLocations(String url) throws Exception;
        List<Comment> getComments(String url) throws Exception;
        List<Service> getServices(String url) throws Exception;

}

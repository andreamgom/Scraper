package es.ulpgc.scraper.database;

import es.ulpgc.scraper.model.Comment;
import es.ulpgc.scraper.model.Location;
import es.ulpgc.scraper.model.Rating;
import es.ulpgc.scraper.model.Service;

import java.sql.SQLException;

public interface BookingDatabase {
    void add(Location locations) throws SQLException;
    void add(Rating ratings) throws SQLException;
    void add(Service services) throws SQLException;
    void add(Comment comments) throws SQLException;
}

package es.ulpgc.scraper.database;

import es.ulpgc.scraper.model.Comment;
import es.ulpgc.scraper.model.Location;
import es.ulpgc.scraper.model.Rating;
import es.ulpgc.scraper.model.Service;

public class DMLTranslater {

    private static final String INSERT_LOCATION =
            "INSERT INTO locations(name, location) VALUES('%s', '%s')";
    public static String insertStatementOf(Location location) {
        return String.format(INSERT_LOCATION,
                location.name,
                location.location);
    }

    private static final String INSERT_RATING =
            "INSERT INTO assessments(name, rating) VALUES('%s', '%s')";
    public static String insertStatementOf(Rating rating){
        return String.format(INSERT_RATING,
                rating.name,
                rating.rating);
    }

    private static final String INSERT_SERVICE =
            "INSERT INTO services(name, service) VALUES('%s', '%s')";
    public static String insertStatementOf(Service service) {
        return String.format(INSERT_SERVICE,
                service.name,
                service.service);
    }

    private static final String INSERT_COMMENT =
            "INSERT INTO comments(name, country, punctuation, review, positive, negative, days) VALUES('%s', '%s','%s', '%s', '%s', '%s', '%s')";
    public static String insertStatementOf(Comment comment) {
        return String.format(INSERT_COMMENT,
                comment.name,
                comment.country,
                comment.punctuation,
                comment.review,
                comment.positive,
                comment.negative,
                comment.days);
    }
}


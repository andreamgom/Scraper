package es.ulpgc.scraper;

import es.ulpgc.scraper.database.BookingDatabase;
import es.ulpgc.scraper.database.SqliteBookingDatabase;
import es.ulpgc.scraper.model.Rating;
import es.ulpgc.scraper.model.Comment;
import es.ulpgc.scraper.model.Location;
import es.ulpgc.scraper.model.Service;
import es.ulpgc.scraper.webservice.BookingSource;
import es.ulpgc.scraper.webservice.Scraper;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BookingSource source = new Scraper();
        BookingDatabase bookingDatabase = new SqliteBookingDatabase();

        String BookingURL_1 = "https://www.booking.com/hotel/es/lopesan-villa-del-conde-resort-thalasso.es.html";
        String BookingURL_2 = "https://www.booking.com/reviews/es/hotel/lopesan-villa-del-conde-resort-thalasso.es.html";
            updateLocations(source, bookingDatabase, BookingURL_1);
            updateRatings(source, bookingDatabase, BookingURL_1);
            updateServices(source, bookingDatabase, BookingURL_1);
            updateComments(source, bookingDatabase, BookingURL_2);

    }
    private static void updateLocations(BookingSource source, BookingDatabase bookingDatabase, String BookingURL_1) throws Exception {
        List<Location> locations = source.getLocations(BookingURL_1);
        for (Location location : locations) bookingDatabase.add(location);
        for (Location location : locations) {
            System.out.println("Nombre: " + location.getName());
            System.out.println("Localización: " + location.getLocation());
            System.out.println("----------------------------");
        }
    }
    private static void updateRatings(BookingSource source, BookingDatabase bookingDatabase, String BookingURL_1) throws Exception {
        List<Rating> ratings = source.getRatings(BookingURL_1);
        for (Rating rating : ratings) bookingDatabase.add(rating);
        for (Rating rating : ratings) {
            System.out.println("Nombre: " + rating.getName());
            System.out.println("Puntuación: " + rating.getRating());
            System.out.println("----------------------------");
        }
    }
    private static void updateServices(BookingSource source, BookingDatabase bookingDatabase, String BookingURL_1) throws Exception {
        List<Service> services = source.getServices(BookingURL_1);
        for (Service service : services) bookingDatabase.add(service);
        for (Service service : services) {
            System.out.println("Nombre: " + service.getName());
            System.out.println("Servicio: " + service.getService());
            System.out.println("----------------------------");
        }
    }
    private static void updateComments(BookingSource source, BookingDatabase bookingDatabase, String BookingURL_2) throws Exception {
        List<Comment> comments = source.getComments(BookingURL_2);
        for (Comment comment : comments) bookingDatabase.add(comment);
        for (Comment comment : comments) {
            System.out.println("Nombre: " + comment.getName());
            System.out.println("País: " + comment.getCountry());
            System.out.println("Puntuación: " + comment.getPunctuation());
            System.out.println("Comentario: " + comment.getReview());
            System.out.println("Positivo: " + comment.getPositive());
            System.out.println("Negativo: " + comment.getNegative());
            System.out.println("Fecha: " + comment.getDays());
            System.out.println("----------------------------");
        }
    }
}
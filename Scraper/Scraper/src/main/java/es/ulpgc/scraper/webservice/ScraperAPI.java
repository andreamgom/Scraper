package es.ulpgc.scraper.webservice;

import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

//http://localhost:4567/hotels/lopesan-villa-del-conde-resort-thalasso.es.html/ratings
//http://localhost:4567/hotels/lopesan-villa-del-conde-resort-thalasso.es.html/comments

public class ScraperAPI {
    public static void main(String[] args) {
        ScraperAPI server = new ScraperAPI();
        server.startServer();
    }

    public void startServer() {
        port(4567);
        staticFiles.location("/public");

        get("/hotels/:name/locations", new GetLocationsCommand());
        get("/hotels/:name/ratings", new GetRatingsCommand());
        get("/hotels/:name/services", new GetServicesCommand());
        get("/hotels/:name/comments", new GetCommentsCommand());
    }

    public Scraper scraper = new Scraper();

    public abstract class ScraperCommand implements Route {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            response.type("application/json");

            String name = request.params(":name");
            if (name == null || name.isEmpty()) {
                response.status(400);
                return "Nombre de hotel no válido";
            }

            String url = "https://www.booking.com/hotel/es/" + name + ".es.html#tab-main";
            Object result = handleScraper(url);
            System.out.println(result);
            return result;

        }
        public abstract Object handleScraper(String url);
    }

    public class GetLocationsCommand extends ScraperCommand implements Route {
        @Override
        public Object handleScraper(String url) {
            return scraper.getLocations(url);
        }
    }

    public class GetRatingsCommand extends ScraperCommand implements Route {
        @Override
        public Object handleScraper(String url) {
            return scraper.getRatings(url);
        }
    }
    public class GetServicesCommand extends ScraperCommand implements Route {
        @Override
        public Object handleScraper(String url) {
            return scraper.getServices(url);
        }
    }
    public class GetCommentsCommand extends ScraperCommand implements Route {
        @Override
        public Object handleScraper(String url) {
            String url1 = "https://www.booking.com/reviews/es/hotel/lopesan-villa-del-conde-resort-thalasso.es.html";
            return scraper.getComments(url1);
        }
    }
}
package es.ulpgc.scraper.database;

import es.ulpgc.scraper.model.Location;
import es.ulpgc.scraper.model.Comment;
import es.ulpgc.scraper.model.Service;
import es.ulpgc.scraper.model.Rating;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteBookingDatabase implements BookingDatabase{

    private final Connection connection;

    public SqliteBookingDatabase() throws SQLException {
        String url = "jdbc:sqlite:DataBase.db";
        connection = DriverManager.getConnection(url);
        initDatabase();
    }
    private static final String TABLA_LOCATIONS =
            "CREATE TABLE IF NOT EXISTS locations(" +
                    "name TEXT, " +
                    "location TEXT);";
    private static final String TABLA_RATINGS =
            "CREATE TABLE IF NOT EXISTS assessments(" +
                    "name TEXT, " +
                    "rating TEXT);";
    private static final String TABLA_SERVICES =
            "CREATE TABLE IF NOT EXISTS services(" +
                    "name TEXT, " +
                    "service TEXT);";
    private static final String TABLA_COMMENTS =
            "CREATE TABLE IF NOT EXISTS comments(" +
                    "name TEXT, " +
                    "country TEXT, " +
                    "punctuation TEXT," +
                    "review TEXT," +
                    "positive TEXT," +
                    "negative TEXT," +
                    "days TEXT);";
    private void initDatabase() throws SQLException{
        connection.createStatement().execute(TABLA_LOCATIONS);
        connection.createStatement().execute(TABLA_RATINGS);
        connection.createStatement().execute(TABLA_SERVICES);
        connection.createStatement().execute(TABLA_COMMENTS);
    }

    @Override
    public void add(Location locations) {
        try {
            connection.createStatement().execute(DMLTranslater.insertStatementOf(locations));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void add(Rating ratings){
        try{
            connection.createStatement().execute(DMLTranslater.insertStatementOf(ratings));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void add(Service services) {
        try {
            connection.createStatement().execute(DMLTranslater.insertStatementOf(services));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void add(Comment comments) {
        try {
            connection.createStatement().execute(DMLTranslater.insertStatementOf(comments));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


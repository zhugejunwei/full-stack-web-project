package routes;

import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;


import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

import com.heroku.sdk.jdbc.DatabaseUrl;

public class JavaGettingStarted {

  public JavaGettingStarted() {
    setupRoutes();
  }

  private void setupRoutes() {
    get("/hello", (req, res) -> "Hello World");

    get("/hi", (req, res) -> "Hi World");

    get("/about", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("title", "About Me");
            attributes.put("name", "Brian J. Kolowitz");
            attributes.put("description", "Dr. Kolowitz's Test Full Stack Site.");
            attributes.put("profession", "Professor");

            return new ModelAndView(attributes, "about.ftl");
        }, new FreeMarkerEngine());

    get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!!!!!");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

    get("/db.html", (req, res) -> {
      Connection connection = null;
      Map<String, Object> attributes = new HashMap<>();
      try {
        connection = DatabaseUrl.extract().getConnection();

        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

        ArrayList<String> output = new ArrayList<String>();
        while (rs.next()) {
          output.add( "Read from DB: " + rs.getTimestamp("tick"));
        }

        attributes.put("results", output);
        return new ModelAndView(attributes, "db.ftl");
      } catch (Exception e) {
        attributes.put("message", "There was an error: " + e);
        return new ModelAndView(attributes, "error.ftl");
      } finally {
        if (connection != null) try{connection.close();} catch(SQLException e){}
      }
    }, new FreeMarkerEngine());
  }
}

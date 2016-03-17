package routes;

import com.google.gson.Gson;
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;


import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

import com.heroku.sdk.jdbc.DatabaseUrl;


public class Week8Routes {

    Gson gson = new Gson();

    public Week8Routes() {
        setupRoutes();
    }

    private void setupRoutes() {
      get("/db.json", (req, res) -> {
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
          return attributes;
        } catch (Exception e) {
          attributes.put("message", "There was an error: " + e);
          return attributes;
        } finally {
          if (connection != null) try{connection.close();} catch(SQLException e){}
        }
      }, gson::toJson);
  }
}

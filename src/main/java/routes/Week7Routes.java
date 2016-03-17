package routes;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import static spark.Spark.get;


public class Week7Routes {

    Gson gson = new Gson();

    public Week7Routes() {
        setupRoutes();
    }

    private void setupRoutes() {
      get("/api/about", (req, res) -> {
          Map<String, Object> data = new HashMap<>();
          data.put("title", "Professor");
          data.put("name", "Brian");
          data.put("description", "INFSCI 2560");
          data.put("profession", "Education");
          return data;
      }, gson::toJson);

        get("/api/time/now", (req, res) -> {
            Map<String, Object> data = new HashMap<>();
            data.put("currentTime", new Date());
            return data;
        }, gson::toJson);

        get("/api/time/now.xml", (req, res) -> {
            Map<String, Object> data = new HashMap<>();
            data.put("currentTime", new Date());
            return data;
        }, gson::toJson);


  }
}

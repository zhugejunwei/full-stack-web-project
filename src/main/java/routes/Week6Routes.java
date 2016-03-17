package routes;

import com.google.gson.Gson;
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Date;

import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

import com.heroku.sdk.jdbc.DatabaseUrl;
import spark.Request;

public class Week6Routes {

    private static boolean shouldReturnHtml(Request request) {
        String accept = request.headers("Accept");
        return accept != null && accept.contains("text/html");
    }

    Gson gson = new Gson();

    public Week6Routes() {
        setupRoutes();
    }

    private void setupRoutes() {
        get("/api/slide15", (req, res) -> {
            Map<String, Object> data = new HashMap<>();
            data.put("format", "json");
            data.put("status", "live");
            return data;
        }, gson::toJson);

        get("/api/slide23", (req, res) -> {
            res.status(302);
            Map<String, Object> data = new HashMap<>();
            data.put("format", "json");
            data.put("status", "live");
            return data;
        }, gson::toJson);

        get("/api/slide27", (req, res) -> {
            Map<String, Object> data = new HashMap<>();
            data.put("status", "live");
            data.put("now", new Date());

            if (shouldReturnHtml(req)) {
                res.status(200);
                return "<pre>" + data + "</pre>";

            } else {
                res.status(200);
                return gson.toJson(data);
            }
        });

        get("/api/slide29", (req, res) -> {
            Map<String, Object> data = new HashMap<>();
            data.put("status", "live");
            data.put("now", new Date());

            if (shouldReturnHtml(req)) {
                res.status(200);
                res.type("text/html");
                return "<pre>" + data + "</pre>";

            } else {
                res.status(200);
                res.type("application/json");
                return gson.toJson(data);
            }
        });

        get("/api/slide31", (req, res) -> {
            Map<String, Object> data = new HashMap<>();
            data.put("status", "live");
            data.put("now", new Date());

            String xml =    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                            "<note>" +
                                "<to>You</to>" +
                                "<from>Me</from>" +
                                "<heading>Reminder</heading>" +
                                "<body>Don't forget me this weekend!</body>" +
                            "</note>";
            res.type("text/xml");
            return xml;
        });
  }
}

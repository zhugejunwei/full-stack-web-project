// import static javax.measure.unit.SI.KILOGRAM;
// import javax.measure.quantity.Mass;
// import org.jscience.physics.model.RelativisticModel;
// import org.jscience.physics.amount.Amount;
//
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.net.UnknownHostException;
import java.net.URI;
import java.net.URISyntaxException;

import static spark.SparkBase.staticFileLocation;
import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

import com.mongodb.*;

import com.heroku.sdk.jdbc.DatabaseUrl;

import static spark.Spark.*;

import routes.*;
import com.todoapp.*;

public class Main {

  public static void main(String[] args) throws Exception {
        //port(Integer.valueOf(System.getenv("10396")));
        staticFileLocation("/public");
        new TodoResource(new TodoService(mongo()));
    }
  
    private static DB mongo() throws Exception {
        MongoClientURI uri = new MongoClientURI("mongodb://heroku_9b1msnk8:qg4gd778v5o71j63bleh4ir0c0@ds015869.mlab.com:15869/heroku_9b1msnk8");
        MongoClient mongoClient = new MongoClient(uri);
        
        String dbname = uri.getDatabase();
        
        mongoClient.setWriteConcern(WriteConcern.SAFE);
        DB db = mongoClient.getDB(dbname);
        if (db.authenticate(uri.getUsername(), uri.getPassword())) {
            return db;
        } else {
            throw new RuntimeException("Not able to authenticate with MongoDB");
        }
    }
}

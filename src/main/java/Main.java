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

import com.todoapp.*;

public class Main {

  public static void main(String[] args) throws MongoException, UnknownHostException {
        MongoClientURI uri = new MongoClientURI(System.getenv("mongodb://heroku_9b1msnk8:qg4gd778v5o71j63bleh4ir0c0@ds015869.mlab.com:15869/heroku_9b1msnk8"));
        MongoClient mongoClient = new MongoClient(uri);
        
        String dbname = uri.getDatabase();
        
        mongoClient.setWriteConcern(WriteConcern.JOURNALED);
        DB db = mongoClient.getDB(dbname);
        
        db.authenticate(uri.getUsername(), uri.getPassword());
        
        staticFileLocation("/public");
        new TodoResource(new TodoService(db));
        
        mongoClient.close();
    }
}

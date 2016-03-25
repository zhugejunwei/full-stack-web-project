// import static javax.measure.unit.SI.KILOGRAM;
// import javax.measure.quantity.Mass;
// import org.jscience.physics.model.RelativisticModel;
// import org.jscience.physics.amount.Amount;
//
import java.util.Arrays;
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.net.UnknownHostException;
//import java.net.URI;
//import java.net.URISyntaxException;
//
//import static spark.SparkBase.staticFileLocation;
//import static spark.Spark.*;
//import spark.template.freemarker.FreeMarkerEngine;
//import spark.ModelAndView;
//import static spark.Spark.get;

//import com.mongodb.*;

import routes.*;
import com.todoapp.*;

import com.mongodb.*;

import static spark.Spark.*;

import java.util.List;

public class Main {

  public static void main(String[] args) throws MongoException, UnknownHostException {
//        MongoClientURI uri = new MongoClientURI(System.getenv("mongodb://heroku_9b1msnk8:qg4gd778v5o71j63bleh4ir0c0@ds015869.mlab.com:15869/heroku_9b1msnk8"));
//        MongoClient mongoClient = new MongoClient(uri);
    //    MongoURI mongoURI = new MongoURI(System.getenv("mongodb://heroku_9b1msnk8:qg4gd778v5o71j63bleh4ir0c0@ds015869.mlab.com:15869/heroku_9b1msnk8"));
        //get connected
    //    DB db = mongoURI.connectDB();
    //    String dbname = "heroku_9b1msnk8";
        
        //mongoClient.setWriteConcern(WriteConcern.JOURNALED);
//        DB db = mongoClient.getDB(dbname);
    //    port(Integer.valueOf(System.getenv("PORT")));

    MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));
    DB db = mongoURI.connectDB();
    db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());

    DBCollection coll = db.getCollection("todos");
    
        staticFileLocation("/public");
    //    Object r = new Week7Routes();
        Object r = new TodoResource(new TodoService(db));
 
//        MongoCredential credential = MongoCredential.createCredential(uri.getUsername(),dbname,uri.getPassword());
    //    MongoCredential credential = MongoCredential.createCredential(mongoURI.getUsername(), mongoURI.getDatabase(), mongoURI.getPassword());
    //    MongoClient mongoClient = new MongoClient(new ServerAddress(), Arrays.asList(credential));
    }
}

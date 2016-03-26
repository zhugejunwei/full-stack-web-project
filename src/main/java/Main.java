import java.util.Arrays;
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

import routes.*;
import com.todoapp.*;

import com.mongodb.*;

import static spark.Spark.*;

import java.util.List;

public class Main {

  public static void main(String[] args) throws MongoException, UnknownHostException, Exception {
        MongoClientURI uri = new MongoClientURI("mongodb://heroku_9b1msnk8:qg4gd778v5o71j63bleh4ir0c0@ds015869.mlab.com:15869/heroku_9b1msnk8");
        MongoClient mongoClient = new MongoClient(uri);
    //    MongoURI mongoURI = new MongoURI(System.getenv("mongodb://heroku_9b1msnk8:qg4gd778v5o71j63bleh4ir0c0@ds015869.mlab.com:15869/heroku_9b1msnk8"));
        //get connected
        String dbname = uri.getDatabase();
        
        //mongoClient.setWriteConcern(WriteConcern.JOURNALED);
        DB db = mongoClient.getDB(dbname);
    //    port(Integer.valueOf(System.getenv("PORT")));

    //MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));
    //DB db = mongoURI.connectDB();
    //db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());

    //DBCollection coll = db.getCollection("todos");
    
        staticFileLocation("/public");
    //    Object r = new Week7Routes();
         new TodoResource(new TodoService(db));
 
        MongoCredential credential = MongoCredential.createCredential(uri.getUsername(),dbname,uri.getPassword());
//        MongoCredential credential = MongoCredential.createCredential(mongoURI.getUsername(), mongoURI.getDatabase(), mongoURI.getPassword());
        //MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
    }
}

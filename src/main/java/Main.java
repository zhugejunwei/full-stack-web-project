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

    public static void main(String[] args) throws Exception {
//	MongoClientURI uri = new MongoClientURI("mongodb://heroku:pn8dcfWw1gaUwhB0Ic62Vr-HCd38d5aCCckox1Hs_4EFlQbre-7225F_2nywCjrjlrCU78bdszTPwjCdEZZeig@candidate.67.mongolayer.com:10396,candidate.68.mongolayer.com:10385/app45876994");
//	MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
//	MongoClient mongoClient = new MongoClient(uri);
//
//        DB db = mongoClient.getDB(uri.getDatabase());
         
        
        MongoURI mongoURI = new MongoURI("mongodb://heroku:pn8dcfWw1gaUwhB0Ic62Vr-HCd38d5aCCckox1Hs_4EFlQbre-7225F_2nywCjrjlrCU78bdszTPwjCdEZZeig@candidate.67.mongolayer.com:10396,candidate.68.mongolayer.com:10385/app45876994");
    //get connected
    
     DB db = mongoURI.connectDB();
     db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());	
///    MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
    // authenticate
    // (version 2.7.2) db.authenticate(mongoURI.getUsername(), mongoURI.getPassword());	
    
///    MongoCredential credential = MongoCredential.createCredential(
///            mongoURI.getUsername(), 
///            mongoURI.getDatabase(), 
///            mongoURI.getPassword());
///         MongoClient mongoClient = new MongoClient(new ServerAddress(), Arrays.asList(credential));
        port(Integer.valueOf(System.getenv("PORT")));
        staticFileLocation("/public");
        
//        DBCollection coll = db.getCollection("testCollection1234");
//        BasicDBObject doc = new BasicDBObject("name", "MongoDB")
//            .append("type", "database")
//            .append("count", 1)
//            .append("info", new BasicDBObject("x", 204).append("y", 103));
//        coll.insert(doc); 
        
///        MongoDatabase db = mongoClient.getDatabase("app45876994");
        
       //DB db = mongoURI.connectDB();
        Object r = new TodoResource(new TodoService(db));
//       Object r = new Week7Routes();
    }
}

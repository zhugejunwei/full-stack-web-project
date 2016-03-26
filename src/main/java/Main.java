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
		
			
		MongoClientURI uri = new MongoClientURI("mongodb://heroku_9b1msnk8:qg4gd778v5o71j63bleh4ir0c0@ds015869.mlab.com:15869/heroku_9b1msnk8");
		String dbname = uri.getDatabase();
		MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
		MongoClient mongoClient = new MongoClient(uri);
		
        mongoClient.setWriteConcern(WriteConcern.SAFE);
        DB db = mongoClient.getDB(dbname);
        
        staticFileLocation("/public");
        new TodoResource(new TodoService(db));
    }
}

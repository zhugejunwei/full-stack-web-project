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
	MongoClientURI uri = new MongoClientURI("mongodb://heroku:pn8dcfWw1gaUwhB0Ic62Vr-HCd38d5aCCckox1Hs_4EFlQbre-7225F_2nywCjrjlrCU78bdszTPwjCdEZZeig@candidate.67.mongolayer.com:10396,candidate.68.mongolayer.com:10385/app45876994");
	MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
	MongoClient mongoClient = new MongoClient(uri);
//	
//        String dbname = uri.getDatabase();
//        
//        mongoClient.setWriteConcern(WriteConcern.SAFE);
        DB db = mongoClient.getDB(uri.getDatabase());
        //port(Integer.valueOf(System.getenv("PORT")));  
        staticFileLocation("/public");
//        new TodoResource(new TodoService(db));
        Object r = new TodoResource(new TodoService(db));
    }
}

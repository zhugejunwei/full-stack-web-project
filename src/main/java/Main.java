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
        port(Integer.valueOf(System.getenv("PORT")));
        staticFileLocation("/public");
        new TodoResource(new TodoService(mongo()));
    }
  
    private static DB mongo() throws Exception {
        String host = "candidate.67.mongolayer.com";
        if (host == null) {
            MongoClient mongoClient = new MongoClient("localhost");
            return mongoClient.getDB("todoapp");
        }
        int port = Integer.parseInt("10396");
        String dbname = "app45876994";
        String username = "heroku";
        String password = "pn8dcfWw1gaUwhB0Ic62Vr-HCd38d5aCCckox1Hs_4EFlQbre-7225F_2nywCjrjlrCU78bdszTPwjCdEZZeig";
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), mongoClientOptions);
        mongoClient.setWriteConcern(WriteConcern.SAFE);
        DB db = mongoClient.getDB(dbname);
        try {
            db.authenticate(username, password.toCharArray());
        } catch(RuntimeException ex) {
            System.err.println("Not able to authenticate with MongoDB" + ex.getMessage());
        }finally{
            return db;
        }
    }
}

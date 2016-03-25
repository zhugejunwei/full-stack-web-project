// import static javax.measure.unit.SI.KILOGRAM;
// import javax.measure.quantity.Mass;
// import org.jscience.physics.model.RelativisticModel;
// import org.jscience.physics.amount.Amount;
//
import java.util.Arrays;

import java.net.UnknownHostException;
//import java.net.URI;
//import java.net.URISyntaxException;
//
//import static spark.SparkBase.staticFileLocation;
//import static spark.Spark.*;
//import spark.template.freemarker.FreeMarkerEngine;
//import spark.ModelAndView;
//import static spark.Spark.get;

import com.mongodb.*;

import static spark.Spark.*;

import com.todoapp.*;
import java.util.List;

public class Main {

  public static void main(String[] args) throws MongoException, UnknownHostException, Exception {
        MongoClientURI uri = new MongoClientURI(System.getenv("mongodb://heroku:pn8dcfWw1gaUwhB0Ic62Vr-HCd38d5aCCckox1Hs_4EFlQbre-7225F_2nywCjrjlrCU78bdszTPwjCdEZZeig@candidate.67.mongolayer.com:10396,candidate.68.mongolayer.com:10385/app45876994"));
        MongoClient mongoClient = new MongoClient(uri);
        
        String dbname = uri.getDatabase();
        
        //mongoClient.setWriteConcern(WriteConcern.JOURNALED);
        DB db = mongoClient.getDB(dbname);

        staticFileLocation("/public");
        new TodoResource(new TodoService(db));
 
        MongoCredential credential = MongoCredential.createCredential(uri.getUsername(),dbname,uri.getPassword());
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
    }
}

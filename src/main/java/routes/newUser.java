package routes;

import com.google.gson.Gson;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Set;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import static spark.Spark.get;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBAddress;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;

import com.heroku.sdk.jdbc.DatabaseUrl;

public class  newUser{
	
	Gson gson = new Gson();
	
	public newUser() {
	        setupRoutes();
	    }
	
	private void setupRoutes(){
    get("/db.user",(req,res) -> {
	try {
    MongoClientURI mongoURI  = new MongoClientURI("mongodb://heroku_9b1msnk8:qg4gd778v5o71j63bleh4ir0c0@ds015869.mlab.com:15869/heroku_9b1msnk8");
    MongoClient mongoClient = new MongoClient(mongoURI);
    DB db = mongoClient.getDB("test");
    DBCollection table = db.getCollection("user");
    /**** Insert ****/
    // create a document to store key and value
    BasicDBObject document = new BasicDBObject();
    document.put("name", "Junwei Zhuge");
    document.put("email", "zhugerock@gmail.com");
    table.insert(document);

    /**** Find and display ****/
    BasicDBObject searchQuery = new BasicDBObject();
    searchQuery.put("name", "Junwei Zhuge");

    DBCursor cursor = table.find(searchQuery);

    while (cursor.hasNext()) {
	System.out.println(cursor.next());
    }
    
    /**** Update ****/
	// search document where name="mkyong" and update it with new values
	BasicDBObject query = new BasicDBObject();
	query.put("name", "mkyong");

	BasicDBObject newDocument = new BasicDBObject();
	newDocument.put("name", "mkyong-updated");

	BasicDBObject updateObj = new BasicDBObject();
	updateObj.put("$set", newDocument);

	table.update(query, updateObj);

	/**** Find and display ****/
	BasicDBObject searchQuery2 
	    = new BasicDBObject().append("name", "mkyong-updated");

	DBCursor cursor2 = table.find(searchQuery2);

	while (cursor2.hasNext()) {
		System.out.println(cursor2.next());
	}

	/**** Done ****/
	System.out.println("Done");
    
    }catch (UnknownHostException e) {
	e.printStackTrace();
    } catch (MongoException e) {
	e.printStackTrace();
    }
	},gson::toJson);
	}
    }
  }
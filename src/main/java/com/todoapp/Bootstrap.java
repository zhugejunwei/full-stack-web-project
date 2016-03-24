/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todoapp;

import com.mongodb.*;

/** # Deprecated
import static spark.Spark.setIpAddress;
import static spark.Spark.setPort;
*/
import static spark.Spark.*;
import static spark.SparkBase.staticFileLocation;
/**
 *
 * @author zhugejunwei
 */
public class Bootstrap {
    //private static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null ? System.getenv("OPENSHIFT_DIY_IP") : "localhost";
    private static final int PORT = System.getenv("ServerAddress.getPort()") != null ? Integer.parseInt(System.getenv("ServerAddress.getPort()")) : 8080;
    private static final String IP_ADDRESS = System.getenv("ServerAddress.getHost()");
    
    public static void main(String[] args) throws Exception {
        ipAddress(IP_ADDRESS);
        port(PORT);
        staticFileLocation("/public");
        new TodoResource(new TodoService(mongo()));
        //MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));
        //DB db = mongoURI.connectDB();
        //MongoCredential credential = MongoCredential.createCredential(mongoURI.getUsername(), mongoURI.getDatabase(), mongoURI.getPassword());	
    }
 
    private static DB mongo() throws Exception {
        String host = System.getenv("MongoClientURI.getHosts()");
        if (host == null) {
            MongoClient mongoClient = new MongoClient("localhost");
            return mongoClient.getDB("todoapp");
        }
        int port = Integer.parseInt(System.getenv("ServerAddress.getPort()"));
        String dbname = System.getenv("MongoClientURI.getDatabase()");
        String username = System.getenv("MongoClientURI.getUsername()");
        String password = System.getenv("MongoClientURI.getPassword()");
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), mongoClientOptions);
        mongoClient.setWriteConcern(WriteConcern.SAFE);
        DB db = mongoClient.getDB(dbname);
        if (db.authenticate(username, password.toCharArray())) {
            return db;
        } else {
            throw new RuntimeException("Not able to authenticate with MongoDB");
        }
    }
}

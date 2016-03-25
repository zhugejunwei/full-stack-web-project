/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todoapp;

import com.mongodb.*;

import java.net.UnknownHostException;

import static spark.Spark.*;
import static spark.SparkBase.staticFileLocation;
/**
 *
 * @author zhugejunwei
 */
public class Bootstrap {

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
        if (db.authenticate(username, password.toCharArray())) {
            return db;
        } else {
            throw new RuntimeException("Not able to authenticate with MongoDB");
        }
    }
}

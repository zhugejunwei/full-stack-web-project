/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.todoapp;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Map;
import java.util.Date;
import java.util.HashMap;
 
import com.google.gson.Gson;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
/**
 *
 * @author zhugejunwei
 */
public class TodoResource {
    private static final String API_CONTEXT = "/api/v1";
 
    private final TodoService todoService;
 
    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
        setupEndpoints();
    }
 
    private void setupEndpoints() {
        Gson gson = new Gson();
        
        get("/api/time/now.xml", (req, res) -> {
            Map<String, Object> data = new HashMap<>();
            data.put("currentTime", new Date());
            return data;
        }, gson::toJson);
        
        post(API_CONTEXT + "/todos", "application/json", (request, response) -> {
            todoService.createNewTodo(request.body());
            response.status(201);
            return response;
        }, new JsonTransformer());
 
        get(API_CONTEXT + "/todos/:id", "application/json", (request, response)
 
                -> todoService.find(request.params(":id")), new JsonTransformer());
 
        get(API_CONTEXT + "/todos", "application/json", (request, response)
 
                -> todoService.findAll(), new JsonTransformer());
 
        put(API_CONTEXT + "/todos/:id", "application/json", (request, response)
 
                -> todoService.update(request.params(":id"), request.body()), new JsonTransformer());
    }
}

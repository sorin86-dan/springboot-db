package com.testing.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testing.utils.DBObject;
import com.testing.utils.RedisCache;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DBService {

    private RedisCache redis = new RedisCache("localhost", 6379);

    public ResponseEntity retrieveDataFromDb (String body) throws IOException {
        var jsonBody = new ObjectMapper();
        var dbObject = jsonBody.readValue(body, DBObject.class);
        var message = redis.get("message");
        var responseBody = jsonBody.writeValueAsString(message + dbObject.getDb());

        return new ResponseEntity(responseBody, HttpStatus.OK);
    }

    public ResponseEntity setDataToDb (String body) throws IOException {
        var jsonBody = new ObjectMapper();
        var dbObject = jsonBody.readValue(body, DBObject.class);
        redis.put("message", dbObject.getMessage());

        return new ResponseEntity("Message updated successfully!", HttpStatus.OK);
    }

}
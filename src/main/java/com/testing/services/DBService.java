package com.testing.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testing.utils.DBObject;
import com.testing.utils.RedisCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Service
public class DBService {

    private RedisCache redis = new RedisCache("127.0.0.1", 6379);
//    private RedisCache redis = new RedisCache("172.0.0.4", 6379);

    public ResponseEntity retrieveDataFromDb (String body) throws IOException {
        var jsonBody = new ObjectMapper();
        var dbObject = jsonBody.readValue(body, DBObject.class);
        var message = redis.get("message");
        if (!StringUtils.hasText(message)) {
            message = "The chosen database is: ";
        }
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

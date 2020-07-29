package com.testing.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testing.utils.DBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class DBService {

    private Logger LOGGER = LoggerFactory.getLogger(DBService.class);

    public ResponseEntity retrieveDataFromDb (String body) throws IOException, URISyntaxException {
        ResponseEntity response;
        var jsonBody = new ObjectMapper();
        var dbObject = jsonBody.readValue(body, DBObject.class);
        var responseBody = jsonBody.writeValueAsString("The chosen database is: " + dbObject.getDb());

        return new ResponseEntity(responseBody, HttpStatus.OK);
    }

}

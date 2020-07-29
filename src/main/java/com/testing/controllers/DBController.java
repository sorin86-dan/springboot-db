package com.testing.controllers;

import com.testing.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class DBController {

    @Autowired
    DBService dbService;

    @PostMapping(value = "/db-message")
    public ResponseEntity getMessage(@RequestHeader("id")String id, @RequestBody String body) throws IOException, URISyntaxException {
        if(!StringUtils.isEmpty(id) && id.equals("OK")) {
            return dbService.retrieveDataFromDb(body);
        }
        return new ResponseEntity("Authorization failed", HttpStatus.UNAUTHORIZED);
    }

}

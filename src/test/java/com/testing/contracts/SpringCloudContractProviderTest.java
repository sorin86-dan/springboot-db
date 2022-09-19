package com.testing.contracts;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SpringCloudContractProviderTest {

    @Autowired
    private WebApplicationContext context;

    @BeforeAll
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(context);
    }
}

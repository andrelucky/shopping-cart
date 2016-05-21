package com.controller;

import com.ShoppingCartApplication;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.model.CartDetail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.*;

/**
 * Created by Andre Luckyanto on 21/05/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShoppingCartApplication.class)
//@Sql(scripts = {"/sample.sql"})
@WebIntegrationTest(randomPort = true)
public class CartControllerTests {
    private static final String BASE_URL = "/api/cart";

    @Value("${local.server.port}")
    int serverPort;

    @Before
    public void setup() {
        RestAssured.port = serverPort;
    }

    @Test
    public void testFindAll() {
        get(BASE_URL)
                .then()
                .statusCode(200);
    }

    @Test
    public void testSave() throws Exception {

        CartDetail cartDetail = new CartDetail();
        cartDetail.setProductId("c53262fc-f5b4-d7de-c794-3f2187afd0f9");
        cartDetail.setQty(1);

        given()
                .body(cartDetail)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(200);

        // qty negatif
        cartDetail = new CartDetail();
        cartDetail.setProductId("6121d546-7336-daa7-e10f-ccd0a57b29ae");
        cartDetail.setQty(-1);
        given()
                .body(cartDetail)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + "/")
                .then()
                .statusCode(400);
    }

    @Test
    public void testDelete() {
        delete(BASE_URL + "/abc123")
                .then()
                .statusCode(200);

        get(BASE_URL + "/abc123")
                .then()
                .statusCode(404);

        // test id salah
        delete(BASE_URL + "/xyz123")
                .then()
                .statusCode(404);
    }
}

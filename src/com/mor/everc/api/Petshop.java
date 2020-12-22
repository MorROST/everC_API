package com.mor.everc.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;



public class Petshop {
public final String URI = "https://petstore.swagger.io/v2/";

    public JsonObject createPet (Pet pet){
        System.out.println("We'll going to create your pet");
        Response response = postRestAction(convertPet(pet), "pet");  //Using extract method, transfer the 'endpoint' as a parameter
        return new Gson().fromJson(response.getBody().asString(), JsonObject.class);  //
    }
    public JsonObject getSinglePet(String id){
        System.out.println("Going to brings the pet: " + id + " from the server");
        Response response = getRestActionById("pet", id);
        return new Gson().fromJson(response.getBody().asString(), JsonObject.class);
    }


    public JsonObject convertPet(Pet pet){   //convert pet structure to JsonObject
        String jsonBody = new GsonBuilder().create().toJson(pet);
        return new Gson().fromJson(jsonBody, JsonObject.class);
    }


    private Response postRestAction(JsonObject body, String endpoint) {
        getRestAssuredConf(endpoint);

        return RestAssured
                .given()
                .basePath(endpoint)
                .contentType(ContentType.JSON)
                .header("accept","application/json")
                .body(body.toString())
                .post();
    }

    //TODO: should adding a 'PUT' scenario to update utility

    private Response getRestActionById(String endpoint,String id) {
        getRestAssuredConf(endpoint);
        return RestAssured
                .given()
                .basePath(endpoint + "/" + id)
                .contentType(ContentType.JSON)
                .header("accept","application/json")
                .get();
    }

    private void getRestAssuredConf(String endpoint) {
        RestAssured.baseURI = URI;
        RestAssured.basePath = endpoint;
        RestAssured.config = RestAssured.config().sslConfig(SSLConfig.sslConfig().allowAllHostnames());
        RestAssured.useRelaxedHTTPSValidation();
    }
}

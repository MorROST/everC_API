package com.mor.everc.api;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Utility {

    public static void printResponse(JsonObject response) {
        if(response != null){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(gson.toJson(response));
        }
    }


    public static Pet convertPetFromResponse(JsonObject response){
        return new Pet()
                .withName(response.get("name").getAsString())
                .withId(response.get("id").getAsInt())
                .withStatus(response.get("status").getAsString())
                .withCategory(new Category()
                                .withName(response.get("category").getAsJsonObject().get("name").getAsString())
                                .withId(response.get("category").getAsJsonObject().get("id").getAsInt()))
                .withPhotoUrls(Arrays.asList(response.get("photoUrls").getAsString()));
    }
}

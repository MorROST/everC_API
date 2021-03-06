package test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mor.everc.api.*;
import org.testng.annotations.Test;
import java.util.Arrays;

public class MorROST {
    @Test
    public void testCreatePet(){
        Pet myPet = new Pet()
                .withId(301630)
                .withName("Unicorn")
                .withCategory(new Category()
                                .withId(62)
                                .withName("unreal")
                )
                .withPhotoUrls(Arrays.asList("https://images-na.ssl-images-amazon.com/images/I/71%2BncdWcmRL.png"))
                .withTags(Arrays.asList(new Tag()
                                .withId(41)
                                .withName("dream")
                ))
                .withStatus("available");

        JsonObject response = new Petshop().createPet(myPet);
        Utility.printResponse(response);
        //TODO: should convert back to 'Pet' and store the result in a storge solution (DB || yaml || txt) and validate
        // that my pet is up to date
    }

    @Test
    public void testGetPet(){
        JsonObject response = new Petshop().getSinglePet("301630");
        Utility.printResponse(response);
        Pet petReceived = Utility.convertPetFromResponse(response);
    }

}

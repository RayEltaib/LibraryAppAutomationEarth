package com.cybertekschool.library.step_definitions;

import com.cybertekschool.library.api.BooksAPI;
import com.cybertekschool.library.utils.api.AuthenticationUtility;
import com.cybertekschool.library.utils.api.Endpoints;
import com.cybertekschool.library.utils.api.LibrarianAuthenticationUtility;
import com.cybertekschool.library.utils.api.StudentAuthenticationUtility;
import com.cybertekschool.library.utils.common.Encoder;
import com.cybertekschool.library.utils.common.Environment;
import com.google.gson.Gson;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.json.Json;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class EditBookStepDefs {
    String accessToken;
    Response response;
    AuthenticationUtility authenticationUtility;

    @Given("the user logged in as {string} in API")
    public void the_user_logged_in_as_in_api(String user) throws Exception {

        switch (user.toLowerCase()) {
            case "librarian":
                authenticationUtility = new LibrarianAuthenticationUtility();
                accessToken = authenticationUtility.getToken();
                break;
            case "student":
                authenticationUtility = new StudentAuthenticationUtility();
                accessToken = authenticationUtility.getToken();
                break;
            default:
                throw new Exception("Wrong user type is provided: " + user);
        }

    }

    @When("the user edit the book using following info")
    public void the_user_edit_the_book_using_following_info(Map<String,Object> map) {


//        response = given().contentType("application/json").accept(ContentType.JSON).header("x-library-token",accessToken).body(map).when().patch(Endpoints.UPDATE_BOOK);
        BooksAPI booksAPI = new BooksAPI();
        response = booksAPI.editBook(map);
//        response.prettyPrint();
    }
    @Then("the correct message should be received")
    public void the_correct_message_should_be_received() {
        String message = response.path("message");
        Assert.assertEquals("The book has been updated.",message);
    }
    @Then("the book info should be changed to as the following")
    public void the_book_info_should_be_changed_to_as_the_following(Map<String,String> map) {

        Response responseGet = given().accept(ContentType.JSON).header("x-library-token",accessToken).pathParam("id",750)
                .when().get("/get_book_by_id/{id}");

        Map<String,Object> actual = responseGet.as(Map.class);

        actual.remove("added_date");
        Assert.assertEquals(map,actual);
    }
}

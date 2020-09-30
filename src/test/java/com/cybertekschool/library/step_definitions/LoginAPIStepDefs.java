package com.cybertekschool.library.step_definitions;

import com.cybertekschool.library.utils.common.Encoder;
import com.cybertekschool.library.utils.common.Environment;
import com.cybertekschool.library.utils.common.LibraryConstants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class LoginAPIStepDefs {

    protected static String accessToken;
    protected static String LibAccessToken;
    protected static String StuAccessToken;
    protected static Response response;

    @When("the user send credentials as {string} to login endpoint")
    public void the_user_send_credentials_as_to_login_endpoint(String user) throws Exception {
        String email = null, password = null;
        switch (user.toLowerCase()) {
            case "librarian":
                email = Environment.getProperty("librarian_email");
                password = Environment.getProperty("librarian_password");
                password = Encoder.decrypt(password);
                break;
            case "student":
                email = Environment.getProperty("student_email");
                password = Environment.getProperty("student_password");
                password = Encoder.decrypt(password);
                break;
            default:
                throw new Exception("Wrong user type is provided: " + user);
        }
            response = given().formParam("email","librarian13@library")
                .formParam("password","9rf6axdD")
                .when().post("login");



    }

    @Then("the status code reads {int}")
    public void the_status_code_reads(int code) {
        accessToken = response.path("token");
        Assert.assertEquals(code , response.statusCode());
    }

    @Given("the user send invalid {string} and {string} to login endpoint")
    public void the_user_send_invalid_and_to_login_endpoint(String userName, String password) {
        response = given().formParam("email",userName)
                .formParam("password",password)
                .when().post("login");

    }


}

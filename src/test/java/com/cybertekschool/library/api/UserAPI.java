package com.cybertekschool.library.api;


import com.cybertekschool.library.utils.api.AuthenticationUtility;
import com.cybertekschool.library.utils.api.LibrarianAuthenticationUtility;
import com.cybertekschool.library.utils.api.StudentAuthenticationUtility;
import com.cybertekschool.library.utils.common.LibraryConstants;
import com.cybertekschool.library.utils.common.LibraryUserUtility;
import io.restassured.response.Response;

import java.util.Map;

import static com.cybertekschool.library.utils.api.Endpoints.ADD_USER;
import static com.cybertekschool.library.utils.api.Endpoints.BOOK_BORROW;
import static io.restassured.RestAssured.given;

public class UserAPI {

    public Map<String, Object> createUser(Map<String, Object> user, String userType) {
        // get a token
        AuthenticationUtility authenticationUtility = new LibrarianAuthenticationUtility();
        String librarianToken = authenticationUtility.getToken();
        // create new user information
        if (userType.equalsIgnoreCase(LibraryConstants.LIBRARIAN)) {
            user = LibraryUserUtility.createUser(2);
        } else if (userType.equalsIgnoreCase(LibraryConstants.STUDENT)) {
            user = LibraryUserUtility.createUser(3);
        }
        // create using using the add_user
        Response response = given().
                header("x-library-token", librarianToken).
                formParams(user).
                log().all().
                when().
                post(ADD_USER).
                prettyPeek();
        response.then().statusCode(200);
        user.put("id", response.path("id"));
        return user;
    }
    public Response borrowBook() {
        // get a token
        AuthenticationUtility authenticationUtility = new StudentAuthenticationUtility();
        String studentToken = authenticationUtility.getToken();
        Response response = given()
                .header("x-library-token", studentToken)
                .formParams("book_id",1,"user_id",1)
                .when().
                        post(BOOK_BORROW).
                        prettyPeek();
        response.then().statusCode(200);
        return response;
    }

}

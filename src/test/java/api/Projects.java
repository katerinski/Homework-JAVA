package test.java.api;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class Projects {
    long newId;

    //Get all projects
    @Test(priority = 1)
    public void getAllProjects() {
        given().
                header("Authorization", "Bearer 44aa875c6fcab413bd731bdcd4ddaffee26703a1").
        when().
                get("https://api.todoist.com/rest/v1/projects").
        then().
                statusCode(200).
        assertThat().
                body(matchesJsonSchemaInClasspath("getBodyValidationArray.json"));

    }

    //Create a new project
    @Test(priority = 0)
    public void createNewProject() {
        /*String jsonBody = "{" +
                "\"name\": \"AQA\"," +
                "\"comment_count\": 0," +
                "\"color\": 47," +
                "\"shared\": false," +
                "\"sync_id\": 0," +
                "\"order\": 1" +
                "}";*/
        newId = given().
                header("Authorization", "Bearer 44aa875c6fcab413bd731bdcd4ddaffee26703a1").
                contentType(ContentType.JSON).
                //body(jsonBody).
                body(new File("createProjectBody.json")).
        when().
                post("https://api.todoist.com/rest/v1/projects").
        then().
                statusCode(200).
                assertThat().body(matchesJsonSchemaInClasspath("getBodyValidationObject.json")).
                extract().
                path("id");
    }


    //Get a project
    @Test(dependsOnMethods = "createNewProject", priority = 2)
    public void getProjectById() {
        given().
                header("Authorization", "Bearer 44aa875c6fcab413bd731bdcd4ddaffee26703a1").
        when().
                get("https://api.todoist.com/rest/v1/projects/" + newId).
        then().
                statusCode(200).
                assertThat().body(matchesJsonSchemaInClasspath("getBodyValidationObject.json"));
    }

    //Update a project
    @Test(dependsOnMethods = "createNewProject", priority = 3)
    public void updateProjectById() {
        String jsonBody = "{" +
                "\"name\": \"AQA4\"" +
                "}";
        given().
                header("Authorization", "Bearer 44aa875c6fcab413bd731bdcd4ddaffee26703a1").
                contentType(ContentType.JSON).
                body(jsonBody).
        when().
                post("https://api.todoist.com/rest/v1/projects/" + newId).
        then().
                statusCode(204);
    }

    //Delete a project
    @Test(dependsOnMethods = "createNewProject", priority = 4)
    public void deleteProjectById() {

        given().
                header("Authorization", "Bearer 44aa875c6fcab413bd731bdcd4ddaffee26703a1").
        when().
                delete("https://api.todoist.com/rest/v1/projects/" + newId).
        then().
                statusCode(204);
    }
}

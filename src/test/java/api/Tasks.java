package test.java.api;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class Tasks {
    long newId;

    @Test(priority = 1)
    public void getAllTasks() {
        given().
                header("Authorization", "Bearer 44aa875c6fcab413bd731bdcd4ddaffee26703a1").
        when().
                get("https://api.todoist.com/rest/v1/tasks").
        then().
                statusCode(200).
                assertThat().
                body(matchesJsonSchemaInClasspath("getBodyValidationTasks.json"));

    }

    @Test(priority = 0)
    public void createNewTask() {
        newId = given().
                header("Authorization", "Bearer 44aa875c6fcab413bd731bdcd4ddaffee26703a1").
                contentType(ContentType.JSON).
                body(new File("createTaskBody.json")).
        when().
                post("https://api.todoist.com/rest/v1/tasks").
        then().
                statusCode(200).
                assertThat().body(matchesJsonSchemaInClasspath("getBodyValidationTask.json")).
                extract().
                path("id");
    }

    @Test(dependsOnMethods = "createNewTask", priority = 2)
    public void getTaskById() {
        given().
                header("Authorization", "Bearer 44aa875c6fcab413bd731bdcd4ddaffee26703a1").
        when().
                get("https://api.todoist.com/rest/v1/tasks/" + newId).
                then().
                statusCode(200).
                assertThat().body(matchesJsonSchemaInClasspath("getBodyValidationTask.json"));
    }

    @Test(dependsOnMethods = "createNewTask", priority = 3)
    public void updateProjectById() {
        String jsonBody = "{" +
                "\"content\": \"Appointment with parents\"" +
                "}";
        given().
                header("Authorization", "Bearer 44aa875c6fcab413bd731bdcd4ddaffee26703a1").
                contentType(ContentType.JSON).
                body(jsonBody).
        when().
                post("https://api.todoist.com/rest/v1/tasks/" + newId).
        then().
                statusCode(204);

    }

    @Test(dependsOnMethods = "createNewTask",priority = 4)
    public void closeTaskById() {
        given().
                header("Authorization", "Bearer 44aa875c6fcab413bd731bdcd4ddaffee26703a1").
        when().
                post("https://api.todoist.com/rest/v1/tasks/" + newId + "/close").
        then().
                statusCode(204);
    }

    @Test(dependsOnMethods = {"createNewTask", "closeTaskById"}, priority = 5)
    public void reopenTaskById() {
        given().
                header("Authorization", "Bearer 44aa875c6fcab413bd731bdcd4ddaffee26703a1").
        when().
                post("https://api.todoist.com/rest/v1/tasks/" + newId + "/reopen").
        then().
                statusCode(204);
    }

    @Test(dependsOnMethods = "createNewTask",priority = 6)
    public void deleteTaskById() {
        given().
                header("Authorization", "Bearer 44aa875c6fcab413bd731bdcd4ddaffee26703a1").
        when().
                delete("https://api.todoist.com/rest/v1/tasks/" + newId).
        then().
                statusCode(204);
    }

}

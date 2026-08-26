package API;

import groovy.lang.DelegatesTo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
//import org.junit.runners.MethodSorters;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardcodedExamples {

    // CRUD operations - create, retrieve, update, delete
    // In each, we do below
    // 1. prepare the request (url + endpoint)
    //2. hit the action - POST, GET, PUT, PATCH, DELETE
    //3. status code and response from API

    String baseURI = RestAssured.baseURI = "https://www.syntaxhrm.com/web/index.php/";
    String token = "Bearer aHJtX3VzZXI6MTc4MzU0NzIxMjo3ZDlkZWZlMzZlODc5ZTQ0MGQ1OTM0NTQwZGUzMmYzNg==";
    public static String empNumber;

    @Test
    public void aCreateEmployee() {

        // prepare the request
         RequestSpecification request = given()
                 .header("Authorization", token)
                 .header("Content-Type", "application/json")
                 .body("{\n" +
                         "  \"firstName\": \"Janki\",\n" +
                         "  \"lastName\": \"Vallabh\",\n" +
                         "  \"middleName\": \"Sita\",\n" +
                         "  \"gender\": \"F\",\n" +
                         "  \"birthday\": \"1990-01-15\",\n" +
                         "  \"job_title\": \"SDET\"\n" +
                         "} ");

        // hitting the endpoint
        Response response = request.when().post("api/v2/pim/create_employee");

        // validate the response
        // validate status code
        response.then().assertThat().statusCode(201);
        // print in API
        response.prettyPrint();
        // validate body, key and value, response header, etc
        // we use assertions of hamcreast matchers (org.hamcrestmatchers)
        response.then().body("data.firstName", equalTo("Janki"));
        response.then().body("data.middleName", equalTo("Sita"));
        response.then().body("data.lastName", equalTo("Vallabh"));
        response.then().header("Content-Type", equalTo("application/json"));
        empNumber = response.jsonPath().getString("data.empNumber");
    }

    @Test
    public void bGetEmployee() {
        RequestSpecification request = given()
                .header("Authorization", token)
                .header("Content-Type", "application/json");

        Response response = request.when().get("api/v2/pim/employee"+"/"+empNumber);
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
    }


    @Test
    public void cPutEmployee() {
        RequestSpecification request = given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"firstName\": \"Jagat\",\n" +
                        "  \"lastName\": \"Vallabh\",\n" +
                        "  \"middleName\": \"Janani\",\n" +
                        "  \"gender\": \"F\",\n" +
                        "  \"birthday\": \"1992-01-25\",\n" +
                        "  \"job_title\": \"SDET\"\n" +
                        "} ");

        // hitting the endpoint
        Response response = request.when().put("api/v2/pim/employee"+"/"+empNumber);

        // validate the response
        // validate status code
        response.then().assertThat().statusCode(200);
        // print in API
        response.prettyPrint();
    }

    @Test
    public void dDeleteEmployee() {
        //RequestSpecification request = given()
          //      .header("Authorization", token)
          //      .header("Content-Type", "application/json")
          //      .body("{\n" +
          //              "  \"ids\": [\n" +
          //             "    " + 3836 + "\n" + // Fixed: Concatenated the variable
          //              //"    " + 3575 + "\n" + // Fixed: Concatenated the variable
           //             "  ]\n" +
           //             "} ");


        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("ids", Collections.singletonList(empNumber)); // Automatically creates the JSON array [empNumber]
        //bodyMap.put("ids", Arrays.asList(empNember1, empNember2, empNember3));      // Use Arrays.asList() to group multiple variables together into one list


        RequestSpecification request = given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .body(bodyMap); // RestAssured converts this map into clean JSON

        // 1. Hit the endpoint and save the response
        Response response = request.when().delete("api/v2/pim/employees");

        // 2. Extract and print the status code to the console
        int statusCode = response.getStatusCode();
        System.out.println("The status code after deletion is: " + statusCode);

        // 3. (Optional) Print the full server message to see the confirmation text
        response.prettyPrint();

        // 4. Validate that it matches your expected code
        response.then().assertThat().statusCode(200);

    }
}


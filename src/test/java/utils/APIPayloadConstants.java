package utils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class APIPayloadConstants {


    // Clean, dynamic Payload Wrapper where you do not write JSON strings (JSON body)
    // using \" escape character. Instead, you write a clean Java utility method that wraps
    // credentials inside a native Java collection (Map) or a dedicated model object (POJO).
    // RestAssured then acts as the "wrapper engine," converting it dynamically to raw JSON at runtime.
    public static Map<String, String> loginPayload(String username, String password) {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", username);
        credentials.put("password", password);
        return credentials;
    }


    // This method is returning a string, not map.
    // Checking response in string format is already done direct in ApiSteps directly
    //public static String createEmployeePayloadMap() {
    //    String createEmployeePayload = "{\n" +
    //            "  \"firstName\": \"Janki\",\n" +
    //            "  \"lastName\": \"Vallabh\",\n" +
    //            "  \"middleName\": \"Sita\",\n" +
    //            "  \"gender\": \"F\",\n" +
    //            "  \"birthday\": \"1990-01-15\",\n" +
    //            "  \"job_title\": \"SDET\"\n" +
    //            "}";
    //    return createEmployeePayload;
    //}

    // 1. TRUE MAP STRATEGY: Returns a Map object instead of a String
    public static Map<String, Object> createEmployeePayloadMap() {
        Map<String, Object> mapPayload = new HashMap<>();
        mapPayload.put("firstName", "Janki");
        mapPayload.put("lastName", "Vallabh");
        mapPayload.put("middleName", "Sita");
        mapPayload.put("gender", "F");
        mapPayload.put("birthday", "1990-01-15");
        mapPayload.put("job_title", "SDET");
        return mapPayload;
    }

    // 2. HARDCODED STRING STRATEGY: Returns a raw JSON String
    public static String createEmployeePayloadJson() {
        // This method is a JSON builder.
        // More specifically, it is a programmatic JSON builder using JSONObject.
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("firstName", "Janki");
        jsonobject.put("lastName", "Vallabh");
        jsonobject.put("middleName", "Sita");
        jsonobject.put("gender", "F");
        jsonobject.put("birthday", "1990-01-15");
        jsonobject.put("job_title", "SDET");
        return jsonobject.toString();
    }

    // 3. DYNAMIC JSON STRATEGY: Returns a dynamically built JSON String
    public static String createEmployeePayloadJsonDynamic
            (String firstName, String lastName, String middleName, String gender, String birthday, String jobTitle) {
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("firstName", firstName);
        jsonobject.put("lastName", lastName);
        jsonobject.put("middleName", middleName);
        jsonobject.put("gender", gender);
        jsonobject.put("birthday", birthday);
        jsonobject.put("job_title", jobTitle);
        return jsonobject.toString();
    }
}

package common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;

public class Library {

    /**
     * Set API endpoint base url and header
     * @param base_url base url of the endpoint
     * @return request request object of the endpoint
     */
    public RequestSpecification setStudentServiceAPIEndpoint(String base_url) {
        RestAssured.baseURI = base_url;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        return request;
    }

    /**
     * Add student to database
     * @param request request object of the endpoint
     * @return response response object of the endpoint
     */
    public Response addStudentToDatabase(RequestSpecification request) {
        Response response = request.body("{\n" +
                "    \"id\": 633289,\n" +
                "    \"firstName\":  \"Mike\",\n" +
                "    \"lastName\": \"Robinson\",\n" +
                "    \"className\": \"4 K\",\n" +
                "    \"nationality\": \"Singapore\"\n" +
                "}")
                .post("/addStudent");
        return response;
    }

    /**
     * Check if student is added to database
     * @param response response object of the endpoint
     * @return isAdded true if added else false
     */
    public boolean checkIfStudentAddedToDatabase(Response response){
        boolean isAdded;
        String jsonString = response.asString();
        isAdded = jsonString != null;
        return isAdded;
    }

    /**
     * Retrieve student from database
     * @param request request object of the endpoint
     * @param paramType query parameter type (class)
     * @param paramValue query parameter value
     * @return students with details
     */
    public Response fetchStudentFromDatabase(RequestSpecification request, String paramType, String paramValue) {
        return request.queryParam(paramType,paramValue).get("/fetchStudents");
    }

    /**
     * Retrieve student from database
     * @param request resuest object of the endpoint
     * @param paramType query parameter type (id)
     * @param paramValue query parameter value
     * @return student with details
     */
    public Response fetchStudentFromDatabase(RequestSpecification request, String paramType, int paramValue) {
        return request.queryParam(paramType,paramValue).get("/fetchStudents");
    }

    /**
     * Check if student is retrieved from database
     * @param response response object of the endpoint
     * @param studentDetailType student detail type (class)
     * @param studentDetailValue student detail value
     * @return isRetrieved true if retrieved else false
     */
    public boolean checkIfStudentRetrievedFromDatabase(Response response, String studentDetailType, String studentDetailValue) throws JsonProcessingException {
        boolean isRetrieved = false;
        String jsonString = response.asString();
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> students = mapper.readValue(jsonString, new TypeReference<List<Map<String, String>>>(){});
//        isRetrieved = students.size() > 0;
//        boolean classNameMatched = false;
        String key = studentDetailType;
        if (studentDetailType.equals("class")){
            key = "className";
        }
        for (Map<String, String> student: students){
            if(student.get(key).equals(studentDetailValue)){
                isRetrieved = true;
            }
        }
        return isRetrieved;
    }

    /**
     * Check if student is retrieved from database
     * @param response response object of the endpoint
     * @param studentDetailType student detail type (id)
     * @param studentDetailValue student detail value
     * @return isRetrieved true if retrieved else false
     */
    public boolean checkIfStudentRetrievedFromDatabase(Response response, String studentDetailType, int studentDetailValue) throws JsonProcessingException {
        boolean isRetrieved = false;
        String jsonString = response.asString();
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> students = mapper.readValue(jsonString, new TypeReference<List<Map<String, String>>>(){});
//        Assert.assertTrue(students.size() > 0);
        String key = studentDetailType;
        if (studentDetailType.equals("Id")){
            key = "id";
        }

        int studentId = Integer.parseInt(students.get(0).get(key));
        isRetrieved = studentDetailValue == studentId;
        return isRetrieved;
    }

    /**
     * Update student record in database
     * @param request request object of the endpoint
     * @return response response object of the endpoint
     */
    public Response updateStudentRecordInDatabase(RequestSpecification request) {
        Response response = request.body("{\n" +
                "    \"id\": 998345,\n" +
                "    \"firstName\":  \"Gary\",\n" +
                "    \"lastName\": \"Thomas\",\n" +
                "    \"className\": \"4 K\",\n" +
                "    \"nationality\": \"Singapore\"\n" +
                "}")
                .put("/updateStudent");
        return response;
    }

    /**
     * Check if student is updated in database
     * @param response response object of the endpoint
     * @return isUpdated true if updated else false
     */
    public boolean checkIfStudentRecordUpdatedInDatabase(Response response){
        boolean isUpdated;
        String jsonString = response.asString();
        isUpdated = jsonString != null;
        return isUpdated;
    }

    /**
     * Delete student record in database
     * @param request request object of the endpoint
     * @param studentDetailType student detail type (id)
     * @param studentDetailValue student detail value
     * @return response response object of the endpoint
     */
    public Response deleteStudentRecordInDatabase(RequestSpecification request, String studentDetailType, int studentDetailValue) {
        Response response = request.body("{\n" +
                "    \""+studentDetailType.toLowerCase()+"\": "+studentDetailValue+"\n" +
                "}")
                .delete("/deleteStudent");
        return response;
    }

    /**
     * Check if student is deleted in database
     * @param response response object of the endpoint
     * @param studentDetailValue student detail value
     * @return isDeleted true if deleted else false
     */
    public boolean checkIfStudentRecordDeletedInDatabase(Response response, int studentDetailValue){
        boolean isDeleted;
        String jsonString = response.asString();
        isDeleted = jsonString.equals("Student with ID " + studentDetailValue + " deleted.");
        return isDeleted;
    }


}

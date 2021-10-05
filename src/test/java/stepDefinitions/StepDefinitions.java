package stepDefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import common.Library;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class StepDefinitions {

    private static final String BASE_URL = "http://localhost:9191";
    private static RequestSpecification request;
    private static Response response;
    Library lib = new Library();


    /**
     * Set API endpoint base url and header
     */
    @Given("A student service api endpoint is available")
    public void setTheStudentServiceAPIEndpoint() {
        request = lib.setStudentServiceAPIEndpoint(BASE_URL);
    }


    /**
     * Add student to the students list
     */
    @When("I add a student to the list")
    public void iAddStudentToTheList() {
        response = lib.addStudentToDatabase(request);
    }


    /**
     * Validate if student is added to the students list
     */
    @Then("Student should be added")
    public void studentShouldBeAdded() {
        Assert.assertTrue(lib.checkIfStudentAddedToDatabase(response));
    }


    /**
     * Retrieve student from database
     * @param paramType query parameter type (class)
     * @param paramValue query parameter value
     */
    @When("I retrieve a student with \"([^\"]*)\" as \"([^\"]*)\"")
    public void iRetrieveAStudentWithAs(String paramType, String paramValue) {
        response = lib.fetchStudentFromDatabase(request, paramType, paramValue);
    }


    /**
     * Retrieve student from database
     * @param paramType query parameter type (id)
     * @param paramValue query parameter value
     */
    @When("I retrieve a student with \"([^\"]*)\" (\\d+)$")
    public void iRetrieveAStudentWith(String paramType, int paramValue) {
        response = lib.fetchStudentFromDatabase(request, paramType, paramValue);
    }


    /**
     * Validate id student is retrieved from students list
     * @param paramType query parameter type (class)
     * @param paramValue query parameter value
     */
    @Then("Students with \"([^\"]*)\" as \"([^\"]*)\" should be retrieved")
    public void studentsWithAsShouldBeRetrieved(String paramType, String paramValue) throws JsonProcessingException {
        Assert.assertTrue(lib.checkIfStudentRetrievedFromDatabase(response, paramType, paramValue));
    }


    /**
     * Validate id student is retrieved from students list
     * @param paramType query parameter type (id)
     * @param paramValue query parameter value
     */
    @Then("Students with \"([^\"]*)\" (\\d+) should be retrieved$")
    public void studentsWithShouldBeRetrieved(String paramType, int paramValue) throws JsonProcessingException {
        Assert.assertTrue(lib.checkIfStudentRetrievedFromDatabase(response, paramType, paramValue));
    }


    /**
     * Validate response code
     * @param expectedResponseCode response code from the response object
     */
    @And("Response code should be (\\d+)$")
    public void responseCodeShouldBe(int expectedResponseCode) {
        Assert.assertEquals(expectedResponseCode, response.getStatusCode());
    }


    /**
     * Update student record
     */
    @When("I update a student record")
    public void iUpdateAStudentRecord() {
        response = lib.updateStudentRecordInDatabase(request);
    }

    /**
     * Validate if student record is updated
     */
    @Then("Student record should be updated")
    public void studentRecordShouldBeUpdated() {
        Assert.assertTrue(lib.checkIfStudentRecordUpdatedInDatabase(response));
    }

    /**
     * Delete student with given details
     * @param studentDetailType student detail type (id)
     * @param studentDetailValue student detail value
     */
    @When("I delete a student record with \"([^\"]*)\" (\\d+)$")
    public void iDeleteAStudentRecordWith(String studentDetailType, int studentDetailValue) {
        response = lib.deleteStudentRecordInDatabase(request, studentDetailType, studentDetailValue);
    }

    /**
     * Validate if student is deleted
     * @param studentDetailType student detail type (id)
     * @param studentDetailValue student detail value
     */
    @Then("Student with \"([^\"]*)\" (\\d+) record should be deleted$")
    public void studentWithRecordShouldBeDeleted(String studentDetailType, int studentDetailValue) {
        Assert.assertTrue(lib.checkIfStudentRecordDeletedInDatabase(response, studentDetailValue));
    }

}

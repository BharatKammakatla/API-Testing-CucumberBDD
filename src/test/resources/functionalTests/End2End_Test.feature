Feature: End to End Tests for Student Services API
  Description: The purpose of this tests are to cover end-to-end happy flows for Student-Services API.

  Background: User can add, update, retrieve and delete student information using CRUD methods of the API.

    Scenario: Add student record
      Given A student service api endpoint is available
      When I add a student to the list
      Then Student should be added
      And Response code should be 201

    Scenario: Fetch student records by class
      Given A student service api endpoint is available
      When I retrieve a student with "class" as "1 B"
      Then Students with "class" as "1 B" should be retrieved
      And Response code should be 200

    Scenario: Fetch student record by id
      Given A student service api endpoint is available
      When I retrieve a student with "Id" 557986
      Then Students with "Id" 557986 should be retrieved
      And Response code should be 200

    Scenario: Update student record
      Given A student service api endpoint is available
      When I update a student record
      Then Student record should be updated
      And Response code should be 200

    Scenario: Delete student record
      Given A student service api endpoint is available
      When I delete a student record with "Id" 633289
      Then Student with "Id" 633289 record should be deleted
      And Response code should be 200
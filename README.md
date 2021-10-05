# RestAPI Automatiion with Cucumber-BDD and Rest-Assured
* This project consists of API tests using BDD approach with Cucumber, Junit and Rest Assured.

## Project Description:
* Project setup with maven
* Written in Java with Junit, Cucumber, Rest Assured & Maven
* Can run test scripts from IDE, command line and Jenkins/CICD pipelines

## Setup:
* Install [Java 11](https://www.oracle.com/hk/java/technologies/javase/jdk11-archive-downloads.html)
* Install Maven [Maven](https://maven.apache.org/)
* Install Sonar [Sonarqube](https://www.sonarqube.org/success-download-community-edition/)

## Run tests:
* Navigate to project root and update `sonar-project.properties` file.
* `mvn clean install` (If you need sonar reports also, then use `mvn clean install sonar:sonar`)

## View HTML Report
* Extent HTML reports will be generated once execution finished - `target/site/site`
* Open `index.html` in browser to see the reports
# virginGames
This is a Proof of Concepts to validate the login functionality for virgin games website using selenium,java

- This is a Maven project which navigates to the virgin games UI and validates the login error messages
-  Main Class which will validate the UI login error messages is ValidateLogin.java under com.validation Package.
- Four Test cases Validated by below test methods
```
  shouldReturnErrorWhenInvalidFormatIsProvided()
  shouldReturnErrorWhenNoPasswordProvided()
  shouldReturnErrorWhenInvalidCredentialsProvided()
  shouldReturnErrorWhenPwdLengthNotExpected()
```
- Config.properties file will store the url and credentials details
- Constants.Java file will store Constants across the Project
- Log4J implemented to log the information and the error occurred in the project.
- pom.xml will store all the maven dependencies
- VirginGamesFrontEndValidationScreenshot is the attached screen shot of successful execution of test cases along with logs displayed.

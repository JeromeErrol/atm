The initial starting number of 50's and 20's can be modified in the application.properties file found in the resources directory.
To test the application manually run the spring boot AtmApplication and open the web browser.

Note: For the purpose of testing all request mappings have been made get requests so that it can be tested in the browser

To display the current state of the atm visit
localhost:8080/

To make a withdrawal visit and enter your request amount value
localhost:8080/{requestAmount}

The following example will try to withdraw 50 from the atm
localhost:8080/50

Unit tests can be found under the test directory in a class called "AtmTests"


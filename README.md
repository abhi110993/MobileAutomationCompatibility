### American Chemical Society(ACS) - Selenium Automation
---
#### System Requirement:

* JDK 1.6 or above
* Maven 3.1 or above
* Eclipse IDE or any other of choice in case there is need to update the script. (optional)
* For execution of scripts on Chrome or Internet explorer you need to have executable files for both drivers respectively and paste them at location "\src\test\resources\drivers" in project folder.
* You can download these executable files from below links
  * Chrome: https://drive.google.com/file/d/0B4FqnK04LJRnNWZFOEE3Wjd4aFk/view
  * Interner Explorer: https://drive.google.com/file/d/0B4FqnK04LJRnbi1nUkc0YzlYUkU/view

#### Execution Steps:
Please follow the instructions to execute the tests on local:

1. Checkout the code from SVN
2. According to the Test Scope use following commands
  - To Execute the single test suite
	``` mvn clean integration-test -Dtest="Test suite name"```
    
#### Result Files:	
The Test Execution Results will be stored in the following directory once the test has completed

    ./target/test-output/emailable-report.html (for complete test suite)
    ./target/surefire-reports/emailable-report.html (for single test suite)

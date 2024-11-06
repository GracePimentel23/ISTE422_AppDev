// Team name:  E P I C   T E A M   N A M E
// Group members: Grace Pimentel, Lauren Carver, Logan Shaw
-------------------------------------------------------------
JDK:
openjdk version "23" 2024-09-17
OpenJDK Runtime Environment Homebrew (build 23)

Gradle:
Gradle 8.10.2
-------------------------------------------------------------
Class	    -       Test Class	    -       Responsible Person

EdgeField	        EdgeFieldTest	        Grace Pimentel
EdgeTable	        EdgeTableTest	        Lauren Carver
CreateDDLMySQL	    CreateDDLMySQLTest	    Logan Shaw
-------------------------------------------------------------
Instructions:

1. cd into the correct directory
    cd src/test

2. gradle clean build
or ./gradlew clean build

3. gradle test  --tests *one file below*
    or ./gradlew test  -tests *one file below*
                -EdgeFieldTest      (Grace)
                -EdgeTableTest      (Lauren)
                -CreateDDLMySQLTest (Logan) 

-------------------------------------------------------------
Known Failing Test(on purpose):

    Grace:
        - varcharValTest
        - dataTypeTest
    Logan:
        none
-------------------------------------------------------------
Disclaimer:
- RLES was acting up for both Logan and Lauren
- Grace used her mac so she did not have issues with RLES - tried commiting for them
- Please run individually
This project is a Spring boot application which is implemeting following login mechanisms.  
1.  Username password based login
2.  Social login(Google)
3.  OTP based login.

**Technologies used:-**
1. Java
2. Mysql
3. Spring(web, security, spring-mysql, jpa)
4. maven
5. JWT

**NOTE:-**
Before running the application create a database by the name `login` and change the `username, password, host` according to your environment in `src/main/resources/application.yml` file.

**Running the project:-**
**Running via terminal:-**
1. clone the project in to the machine.
2. Go to the cloned location using terminal.
3. Chnage the Database credentials as mentioned in above **NOTE** section according to your environment.
4. run `mvn clean package -DskipTests=true`
5. once above command ran successfully a `jar` file will be generated under `src/target` with the name `LoginApp.jar`.
6. run `java -jar LoginApp.jar`. Your application must be running on port 8080 if it not occupied some other process.


**Running in eclipse:-**
1. After cloning the project open the eclipse.
2. click on `file --> import project --> Maven --> existing maven projects`. Browse the cloned path and select `Finish`.
3. Let the project download all the dependencies from the pom.xml file. This might take some time depending upon the internet connectivity.
4. Change the Database properties as mentioned in the **NOTE** section.
5. `Right clck on Project --> run as --> java application --> choose LoginApplication.java`
6. Or Open `LoginApplication.java  Right click-->run as-->java application'. This runs the application on port 8080 if it is not occupied by any other process.

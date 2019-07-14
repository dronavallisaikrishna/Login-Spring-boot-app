FROM openjdk:8
EXPOSE 8080
ADD target/LoginApp.jar LoginApp.jar 
ENTRYPOINT ["java","-jar","/LoginApp.jar"]
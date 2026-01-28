# Requirement to setup
1. Jdk 21
2. Postgresql
3. Intelij to run on local

# Instructions to run the service on local
1. Install jdk 21
2. Clone the git project and open by intelij (as maven project)
3. Install postgresql and pgadmin
4. Create new DB with name examedia-hospital
5. Change username and password in application.yml under resources
6. Reload pom dependency
7. mvn clean install
8. run the main application
9. open postman
10. load the postman collection under resources folder
11. if want jar file, mvn clean package

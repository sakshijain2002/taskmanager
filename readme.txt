SpringBoot Application running on localhost 8080
jdk 17 is required

/*Database setup:
url in application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/data_base name
create a database in mysql

API:
USING POSTMAN
for add task in database url:http://localhost:8080/api/tasks/saveTask

>>GET ALL task LIST:http://localhost:8080/api/tasks

>>DELETE task:http://localhost:8080/api/tasks/(id of employee which you want to delete)

>>Update task by id: http://localhost:8080/api/tasks/id

for security:
spring.security.user.name=
spring.security.user.password=
you can specify the username and password
also in security config class








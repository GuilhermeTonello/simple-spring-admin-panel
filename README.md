# Spring Admin Panel

## Sumary
- About
    - About the project
	- Project reason
	- Dependencies
- Installation
	- Download
	- Configuration
	    - Database
	    - Security
	    - Users
- Running the project

## About

### About the project
This is a simple administration panel on the web for management of products and categories.

### Project reason
The reason for this project was to learn more about the frameworks used in this project.

### Dependencies
**Note**: the dependencies are managed by maven on pom.xml.

###### Spring
For more details about Spring Frameworks access: https://spring.io/projects
- Spring Web
- Spring Data JPA
- Spring Security
- Spring Boot DevTools

###### Thymeleaf
For more details about Thymeleaf access: https://www.thymeleaf.org/
- Thymeleaf
- Thymeleaf Extras Spring Security

###### PostgreSQL
For more details about PostgreSQL access: https://www.postgresql.org/
- PostgreSQL JDBC Driver

###### Hibernate Validator
For more details about Hibernate Validator access: https://hibernate.org/validator/
- Hibernate Validator

###### Hibernate Types
For more details about Hibernate Types access: https://github.com/vladmihalcea/hibernate-types
- Hibernate Types

###### Materialize
For more details about Materialize access: https://materializecss.com/
- Materialize

## Installation

### Download
To download this project use:
```sh
$ git clone https://github.com/GuilhermeTonello/simple-spring-admin-panel.git
```

### Configuration
**Note**: ***If you don't know what you are doing, I recommend you to just configure the database changing only*** *spring.datasource.url*, *spring.datasource.username* ***and*** *spring.datasource.password*.

#### Database configuration
1. Open the file **application.properties** localized in **src/main/resources**
2. Change the following configurations as you like
    - *spring.jpa.hibernate.ddl-auto*
        - Configure the schema
        - Options for this configuration area:
            - create: create the schema destroying previous data
            - create-drop: create the schema, but destroy it typically when close the application
            - update: update the schema
            - validate: validate the schema, no changes on database
            - none: does nothing, no changes on database
    - *spring.datasource.url*
        - The database url
    - *spring.datasource.username*
        - The username for the database
    - *spring.datasource.password*
        - The password for the database
    - *spring.jpa.show-sql*
        - If enabled, this will show the SQL on the console.
        - To disable, delete this propertie or change the value to *false*
    - *spring.jpa.properties.hibernate.format_sql*
        - If the previous propertie is enabled, this will format the SQL showed on console.
        - To disable, delete this propertie or change the value to *false*
    - *spring.datasource.initialization-mode*
        - Populate the database with initial data.
        - To disable that, delete this propertie and delete data.sql, more information on the next topic
3. If you want to delete the initial data, delete **data.sql** on **src/main/resources** and delete the propertie *spring.datasource.initialization-mode* on **src/main/resources/application.properties**.
**Note**: ***I don't recomend you to delete that if you don't know what you are doing***
4. If you want to change the database you will have to change **PostgreSQL dependency** on **pom.xml**
**Note**: ***Changing the database may cause errors on users table due to the jsonb type***

#### Security configuration
**Note**: ***This section is for people who knows how to use Spring Security***
1. You will need to know about **Spring Security** to configure this, to learn more, see https://docs.spring.io/spring-security/site/docs/5.4.5/reference/html5/
2. To configure the security, access **src/main/java/com/gui/adminpanel/security/ApplicationSecurityConfiguration.java**
3. To configure the password encoding, access **src/main/java/com/gui/adminpanel/security/PasswordEncoderConfiguration.java**
4. The roles can be configured on database on table **ROLES**

#### Users configuration
**Note**: If you have deleted **data.sql** and/or deleted/disabled *spring.datasource.initialization-mode*, the default users and roles won't work and you will have to configure it manually on the database

1. Default users:
    - Admin user:
        - Username: admin
        - Password: admin_password
        - Role: ADMIN
    - Employee user:
        - Username: employee
        - Password: employee_password
        - Role: EMPLOYEE
    - Trainee user:
        - Username: trainee
        - Password: trainee_password
        - Role: TRAINEE
2. The default roles are *ADMIN*, *EMPLOYEE* and *TRAINEE*

## Running the project
1. After the configuration, to run it, follow the next instructions
2. Go to the project root. This is where you have the **pom.xml** file, **src** folder etc
3. Open the terminal on this folder
4. Type the following on terminal and press ENTER:
    - MacOS/Linux
		```sh
		$ ./mvnw spring-boot:run
		```
    - Windows
		```sh
		$ mvnw spring-boot:run
		```
5. Open a browser, and on the search bar, use this: **localhost:8080**
6. If you already have the port 8080 running, go to **src/main/resourcers/application.properties** and add this: *server.port=YOUR PORT HERE*. Example: *server.port=8081*. Then **Ctrl + C** to stop the project and run it again following the previous topic.
7. To close the project, type **Ctrl + C** on terminal and press ENTER.

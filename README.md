# Angular and Spring Boot

### Project Structure
This example project breaks the Angular and Spring Boot applications into two Maven projects, frontend and backend,
respectively. The frontend project is a dependency of the backend project, so the final jar file contains the frontend
jar. This makes it easy to deploy because there is only one jar file, and only one server needed to run the frontend and
backend.

For local development the two projects are run on their own separate servers, and the frontend project is configured to
redirect all network calls that begin with '/api' to the backend server.

### Set up
Clone the project and run `mvn clean install` in the project root. This will install all the Maven and Angular
dependencies. Then you can import the project into an IDE of your choice.

#### Auto refresh
Make sure your IDE can be configured to automatically build the project. In IntelliJ IDEA you can do this by going to
Preferences => Build, Execution, Deployment => Compiler, and then checking the box that says 'Build project
automatically'. This will trigger a project build every time you save changes in the backend project.

A project build triggers the [spring-boot-devtools](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-devtools.html) dependency in the backend project to do a hot swap. With this working
you can install the [livereload](http://livereload.com/extensions/) browser extension, which will cause your browser to
refresh every time you save changes in the backend project.

### Run project locally
To start the backend server run `mvn spring-boot:run` from the backend directory. To start the frontend server run
`npm start` from the frontend directory. In your IDE you can make run configurations for both of those commands.

If you set up the project as described in the set up section above you should be able to open your browser to
http://localhost:4200 and your browser will automatically refresh every time you save changes in the frontend and
backend projects.

### Quickstart
To use this project as a quickstart you can clone the project and then delete the .git directory. Then use `git init`
and `git remote add origin [url-to-your-repo]` to add the project to your own git repository. Then change the project
name by editing the pom.xml files in all three projects, and the angular settings files in the frontend project.
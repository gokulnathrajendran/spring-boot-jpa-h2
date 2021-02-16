# spring-boot-jpa-h2
Transamerica Weekend Project

# Short Intro
Have a basic rest api calls for the Redbox movie rental service.


# Model
Movie is the model used here and below are the parameters
- `id`:`Long` - Primary key for the Movie Disc
- `title`: `String` - Describes the Movie Name of the Disc.
- `Description`: `String` - Describes the description of the movie.
- `Available`: `Boolean` - Lets the machine know if the Movie disc is available or not. 

# H2 Database
`Console Path` - h2-ui
Database name is jdbc:h2:mem:testdb
Database schema `init.sql` contains the schema under Resources.

# API Calls
`http://localhost:8080/api` - Default Spring API  
`GET` - `/movies` - List all the movies   
`GET` - `/movies/{id}` - List the movie based on the id.  
`POST` - `/movies` - Creates the Movie in the database using Service and DAO layer  
`PUT` - `/movies` - Update the Movie data.  
`DELETE` - `/movies/{id}` - Delete the movie from the Database.  
`DELETE` - `/movies` - Delete all the movies in the Database.  

`NOTE`- all the endpoints are ended as movies just for demo purposes.

# Executing Instructions
mvn clean install  
mvn spring-boot:run

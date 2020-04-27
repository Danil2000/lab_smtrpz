<h3>To run the Eureka service with Maven, run the following command in a terminal window:</h3>
<tt>./mvnw spring-boot:run -pl eureka-service</tt> 

<h3>To run the Eureka client with Maven, run the following command in a terminal window:</h3>
<tt>./mvnw spring-boot:run -pl eureka-client</tt> 

<h3>DB uses the one only table Students</h3>
<tt>create table students(id int primary key, name varchar(255), url varchar(255));</tt>
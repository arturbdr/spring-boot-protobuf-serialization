# spring-boot-protobuf-serialization

### Project in this repo
- spring-boot-serializations-producer: responsible for produced Protobuf and json encoded data. As default, this project start on port 8080
and exposes 2 endpoints: one to expose JSON and one to expose Protobuf.

- spring-boot-serializations-consumer: responsible for consuming the endpoint of the producer (project above). As default, this project start on port 8081
and depends on the producer to work properly. The main purpose of this project is to show how to consume endpoint that exposes Protobuf encoded data.
Check the class `ProducerRestTemplate` to see the implementation.

### Frameworks and libraries used
- Spring Boot version 2.5.3 - For exposing the RESFull endpoint for both Protobuf objects as well as JSON also for Dependency Injection and all testing platform
- Lombok - To reduce Java verbosity using his annotations


### How to start locally
- Requirements: You need to have both JDK 11 and Protobuf compiler installed.
- Important: check the installed version of protobuff. It should match the same version specified in the `pom.xml` protobuf-maven-plugin
- Both projects contains a .proto file that need to be compiled by the protobuf compiler. Both projects also contains a plugin to generate a respective .java class from by the proto file. Just type `mvn protobuf:compile` in the pom of any one of the projects.

### PR/Issues/Doubt
- Feel free to suggest improvements and/or ask doubts or point issues

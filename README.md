# spring-boot-protobuf-serialization

### Project in this repo
- spring-boot-serializations-producer: responsible for produced mocked data. As default, this project start on port 8080
and exposes 2 endpoints: one to expose JSON and one to expose Protobuf 
- spring-boot-serializations-consumer: responsible for consuming the endpoint of the producer. As default, this project start on port 8081
and depends on the producer to work properly. The main purpose of this project is to show how to consume endpoint that exposes Protobuf information.
Check the class `ProducerRestTemplate` to see the implementation.

### Frameworks and libraries used
- Spring Boot version 2.1.2 - For exposing the RESFull endpoint for both Protobuf objects as well as JSON also for Dependency Injection and all testing platform
- Lombok - To reduce Java verbosity using his annotations


### How to start locally
- Requirements: You need to have both JDK and Protobuf compiler installed.
- Both projects contains a .proto file that need to be compiled by the protobuf compiler. Both projects also contains a plugin to generate a respective .java class from by the proto file. Just type `mvn protobuf:compile` in the pom of any one of the projects.

### PR/Issues/Doubt
- Feel free to sugest improvements and/or ask doubts or point issues

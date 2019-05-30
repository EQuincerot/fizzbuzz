## Project Description 

Fizzbuzz API endpoint.

### Requirements
- open-jdk 11

### Run
To start the server, execute:

    ./gradlew bootRun

The port override is available through the `-Dserver.port=PORT` option.

### Api

The API is available at http://localhost:8080/api/fizzbuzz

A swagger documentation is available at http://localhost:8080/swagger-ui.html

### Packaging
To build a jar, please run:

    ./gradlew build
    
A jar is built in build/libs/fizz-buzz-equincerot-0.1.0.jar

The server can be started with:

    java -jar build/libs/fizz-buzz-equincerot-0.1.0.jar
    
Or if you want to change the default 8080 port with `$PORT`:

    java -jar -Dserver.port=$PORT build/libs/fizz-buzz-equincerot-0.1.0.jar
    
### Docker Usage
To build a docker image, use the following command:

     docker build --build-arg=JAR_FILE="build/libs/fizz-buzz-equincerot-0.1.0.jar" -t equincerot/fizzbuzz:latest .
     
 Once the image is built, you can run a new container:
 
    docker run --rm -p 8888:8080 equincerot/fizzbuzz:latest
    
with 8888 the host port of your choice.

### IDE configuration
For IntelliJ IDEA, run:

    ./gradlew idea

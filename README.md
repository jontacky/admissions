

After starting the application it is accessible under `localhost:3030`.

## Build

The application can be built using the following command:

```
mvnw clean package
```

The application can then be started with the following command - here with the profile `production`:

```
java -Dspring.profiles.active=production -jar ./target/admissions-0.0.1-SNAPSHOT.jar
```
 

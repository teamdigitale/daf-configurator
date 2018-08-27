# daf-configurator
An example of a skeleton for writing java and scala microservices using an Api contract first approach using play 2.6 and swagger code gen

#### Building

It uses maven swagger code gen plugin for starting from an openapi 2.0 (3.0 is still not supported) file specification for generated a skeleton of a Java Play with controllers apiModels ecc... but it is based on Scala 2.12 and sbt 13.3.

[app/controllers](controllers)


#### Steps for running locally

```sh
mvn clean compile
sbt
compile
run
```

#### Steps for publishing on daf test

```sh
mvn clean compile
sbt
eval System.setProperty("STAGING", "true")
reload
compile
docker:publish
```

#### Steps for publishing on daf production

```sh
mvn clean compile
sbt
compile
docker:publish
```
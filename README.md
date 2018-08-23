# Word Counting Project

[![Build Status](https://travis-ci.org/irimia-dragos/wordCountingProject.png)](https://travis-ci.org/irimia-dragos/wordCountingProject)

This is a sample project.

Do not use in production.



# Building

This project is build by maven.

You must have maven installed in order to build this project.
```
mvn clean install
```
The generate jar will be available in the target folder

In order to run the project one could use the follwing command:
```
java -jar wordCounting/target/wordCounting-1.0-SNAPSHOT.jar tempest.txt
```

# Java 9 support
In case maven will detect java 9 jre, it will switch to WordParserStreams implementation.

# Microbenchmarking
The project uses JMH to do micro benchmarks on the WordCounting project.
This is separated in a different project.
To run the test, one could use the following command:
```
java -jar jhm/target/benchmarks.jar
```


# More info
There is also a short description in the project [wiki page](https://github.com/irimia-dragos/countingWordsProject/wiki)

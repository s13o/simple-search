# simple-search
A kind of test assignment with design goals:
 - abstraction (the ability to use the resulting system without knowing all of its internal details) 
 - modularity (the ability to factor the system into smaller, simpler pieces that can be more easily understood 
 and/or replaced with other pieces)

## requirements

Your service should work with small documents where each document contains a series of tokens (words). 
To keep things simple document can be represented as String.
 
The usage model of service:
- Put documents into the search engine by key 
- Get document by key 
- Search on a string of tokens to return keys of all documents that contain all tokens in the set

For index persistence you can store documents in server's memory.
 
To keep things simple we can assume that there will be no overwrites of a key with a new document.

You should not use existing tools like Lucene based solution, Sphinx or similar.

Simplest static configuration could be used (no service discovery, replicas, balancing etc.).


## implementation details

- [index-api](./index-api/pom.xml) store base interfaces. Documentation addded to JavDoc
- [index-simple](./index-simple/pom.xml) store simple implementation with in-memory storing of Documents
- [server](./server/pom.xml) is under construction. It should be a Spring Boot Rest Application with simple React.JS client

 ## compilation & tests
To compile and run test, pls, run in a command line at root folder of the project 
(Maven & JDK8 are required to be installed before)
 ``
 mvn clean install
 ``

[BDD](https://cucumber.io/) Tests are:

 - [SimpleTokFactoryTest](./index-simple/src/test/resources/SimpleTokFactoryTest.feature):
 To test basic functions of "TokenFactory" & "Tokenizer" (how to split big Document on set of Tokens) 
 - [SimpleVocabularyTest](./index-simple/src/test/resources/SimpleVocabularyTest.feature):
 To test Vocabulary - how it store links between Tockens and Documents
 - [SimpleIndexTest](./index-simple/src/test/resources/SimpleIndexTest.feature): 
 Basically a real test of the index. You can easily play with the feature file and run the test again
 
 
 The ::SimpleIndexTest:: is enough to check how the Index works even without test GIU application. 
 It is a kind of CLI-test but some more comfortable as for me because it is a "played scenario"
 and you need the only simple text editor to modify test data and repeat the test again.

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

*TBD*
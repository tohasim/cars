# READ ME
## What are the benefits of using a RESTful API
- It uses the standard HTTP methods (GET, POST, PUT, DELETE, etc)
- Statelessness, thus it is easily scalable
- Can make use of caching to free up server resources

## What is JSON, and why does JSON fit so well with REST?
JSON is a text-based way to interpret data. \
Since it is text-based it can be understood by any framework/language, which makes it so good for REST APIs.
## How you have designed simple CRUD endpoints using spring boot and DTOs to separate api from data  -> Focus on your use of DTO's
I have created Request and Response DTOs to manipulate my data from the repository to the API. 
## What is the advantage of using using DTOs to separate api from data structure when designing rest endpoints
Better control over which information is sent to the api.

## Explain shortly the concept mocking in relation to software testing
Mocking is needed when unit testing a class with dependencies to other classes. \
By mocking these dependencies you can circumvent having to wait for a slow DB to start up, or just avoid having to get data from other classes.

## How did you mock database access in your tests, using an in-memory database and/or mockito → Refer to your code
I used the @DataJpaTest annotation, which implies the use of an in-memory h2 database.

## Explain the concept Build Server and the role Github Actions play here
A build server is a server on which you build your application in order to check that everything works as intended before the code goes live. \
If anything fails, the developers should be notified. GitHub actions does this. 

## Explain maven, relevant parts in maven, and how maven is used in our CI setup. Explain where maven is used by your GitHub Actions Script(s)


## Understand and chose cloud service models (IaaS, PaaS, SaaS, DBaaS)for your projects -> Just explain what you have used for this handin

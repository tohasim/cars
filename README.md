# READ ME
## The idea with, and reasons for why to use, a ORM-mapper
A ORM mapper is a great way to automize and simplify your code, while programming object oriented applications with relational databases

## The meaning of the terms JPA, Hibernate and Spring Data JPA and how they are connected
JPA is a specification, which hibernate is an implementation of. Spring Data JPA is an abstraction if that implementation, which helps us avoid a lot of boilerplate code

## How to create simple Java entities and map them to a database via the Spring Data API
You create the Java class, which should have the @Entity and @Id annotations, and a zero argument constructor. From here you just implement the class as a JpaRepository.

## How to control the mapping between individual fields in an Entity class and their matching columns in the database
With the @Columns annotation you can control the parameters of the column in the DB

## How to auto generate IDs, and how to ensure we are using  a specific database's preferred way of doing it (Auto Increment in our case for  MySQL)
When you have annotated your @Id value, you can generate the value of it with @GeneratedValue and give the strategy = GenerationType.IDENTITY as a argument

## How to use and define repositories and relevant query methods using Spring Data JPAs repository pattern
Create an interface for the repository, and extend JpaRepository<Type, Id-type>. After this you can add more functionality, by adding more methods to the interface.

## How to write simple "integration" tests, using H2 as a mock-database instead of MySQL
Add a test file under the test folder, and add the @DataJpaTest annotation to the class, add an @Autowired annotation, and then add your @Test's

## How to add (dev) connection details for you local MySQL database
As environment variables under the "Edit configurations" tab.
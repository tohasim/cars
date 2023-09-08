# README week 3
### Where and why you have used a @OneToMany annotation
I have used OneToMay annotations for the reservations field in my Car and Member classes to define the bidirectional relation between them.

### Where and why you have used a @ManyToOne annotation
Same as above, but reversed.

### The purpose of the CascadeType, FetchType and mappedBy attributes you can use with one-to-many
CascadeType is to define what should happen to orphans when an entry in a table is deleted. 
FetchType is to control whether the many relation is fetched at initialization or only later if and when it is used.
mappedBy is to control which field the class maps to in the relation.

### How/where you have (if done) added user defined queries to you repositories
I have added them in my Repository class with a @Query annotation.

### a few words, explaining what you had to do on your Azure App Service in order to make your Spring Boot App connect to your Azure MySqlDatabase
I just had to add the connection details as enviromnent values in the configuration tab

### a few words about where you have used inheritance in your project, and how it's reflected in your database
I used inheritance with my users, after implementing the security package. It is reflected in the user_with_roles table and its discriminator_type column

### how are passwords stored in the database with the changes suggested in part-6 of the exercise
Encrypted
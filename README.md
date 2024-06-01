# Unit 18 ORM Hibernate (Part 1)
<details>

<summary>1 - Spring Initializr setup for Hibernate and Spring Data JPA</summary>

- Language: Java
- Project: Maven
- Spring Boot: 3.3.0
- Project Metadata:
    - Group: com.coderscampus
    - Artifact: Unit_18_Hibernate_2
    - Name: Unit_18_Hibernate
    - Description: Demo project for Spring Boot and Hibernate
    - Package name: com.coderscampus.Unit_18_Hibernate_2
- Packaging: Jar
- Java: 17
- Dependencies:
    - Spring Web
    - Spring Data JPA
    - Thymeleaf
    - MySQL Driver
    - Spring Boot DevTools
</details>

[//]: # (## 1 - Spring Initializr setup for Hibernate and Spring Data JPA)

[//]: # (- Language: Java)

[//]: # (- Project: Maven)

[//]: # (- Spring Boot: 3.3.0)

[//]: # (- Project Metadata:)

[//]: # (    - Group: com.coderscampus)

[//]: # (    - Artifact: Unit_18_Hibernate_2)

[//]: # (    - Name: Unit_18_Hibernate)

[//]: # (    - Description: Demo project for Spring Boot and Hibernate)

[//]: # (    - Package name: com.coderscampus.Unit_18_Hibernate_2)

[//]: # (- Packaging: Jar)

[//]: # (- Java: 17)

[//]: # (- Dependencies:)

[//]: # (    - Spring Web)

[//]: # (    - Spring Data JPA)

[//]: # (    - Thymeleaf)

[//]: # (    - MySQL Driver)

[//]: # (    - Spring Boot DevTools)

## 2 - Prepping Code for Hibernate
`domain` package name means the contents/objects in here refer to database tables
- First we created a `User` class in the `domain` package and that implies we have a `User` database table

At the end of the day we perform CRUD opperations
- Create data
- Read data
- Update data
- Delete data

Our jobs are in data creation and management

## 3 - Mapping an Entity
1. Need to put `@Entity` above the `public class ClassName`, normally found within `domain` package. This tells Hibernate that their is a database table assosciate with this Object. Hibernate is the ORM (Object Relational Mapping) tool that maps Java classes to database tables and visa versa.
- `@Entity` annotation means this entire Class is an Entity.
- Entity means an Object that represents a database table
- Hibernate abides by the JPA (Java Persistence API)
- JPA is a contract that says, if you implement X,Y,Z you need to abide by these rules.
    - JPA is a specification (a set of interfaces and rules) defined by Java EE (now Jakarta EE) for object-relational mapping (ORM) and persistence management in Java applications
2. Because `user` is a keyword in SQL we need to use `@Table(name="users")` and attach that annotation beneath `@Entity`. This overrides the database table it would have been, aka `user`, and change it so their are no conflicts with SQL.
3. Assign a primary key. In Java you do that be creating an instance variable, `private Long userId;`, make your Getters and Setters, and above the Getter you'll attach the `@Id` annotation.
4. Because we want the primary key auto-incremented, we'll add `@GeneratedValue(strategy = GenerationType.IDENTITY)` beneath the primary key, `@Id`, above the instance variable for `userId`.

<!-- Hashnode has all the above content -->

What we're doing here is putting in Java code annotations that help Hibernate put together a `create table` SQL script.

## 4 - Configuring your DB in Properties file

1. In `application.properties` file I added
```text
spring.datasource.driverClassName = com.mysql.cj.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/hibernate_example_2
spring.datasource.username = ${MYSQL_USERNAME}
spring.datasource.password = ${MYSQL_PASSWORD}
# --- none, create, create-drop, or update ---
spring.jpa.hibernate.ddl-auto = create
spring.jpa.show-sql = true
spring.thymeleaf.prefix=file:src/main/resources/templates/
spring.devtools.livereload.enabled=true
# --- UNCOMMENT LINE BELOW FOR DEBUGGING ASSISTANCE ---
logging.level.org.springframework.web=DEBUG
```

2. Create a `.evn` file in the root folder of project and attach
```text
MYSQL_USERNAME=your_username_here
MYSQL_PASSWORD=your_password_here

```

3. Add the dotenv dependency to pom.xml file.
```Java
  <dependency>
			<groupId>io.github.cdimascio</groupId>
			<artifactId>java-dotenv</artifactId>
			<version>5.2.2</version>
	</dependency>
```

4. Right click on root project folder > Maven > Reload Project
5. Add the following content to the `main` method of project or to your `Unit18HibernateApplication.java` file.

```Java
package com.coderscampus.Unit_18_Hibernate_2;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Unit18HibernateApplication {

	public static void main(String[] args) {
		
//	---	Content added ---
		Dotenv dotenv = Dotenv.load();
		System.setProperty("MYSQL_USERNAME", dotenv.get("MYSQL_USERNAME"));
		System.setProperty("MYSQL_PASSWORD", dotenv.get("MYSQL_PASSWORD"));
//	---	End of content added ---
		SpringApplication.run(Unit18HibernateApplication.class, args);
	}
}
```

6. Add these lines to `.gitignore` file
```text
### Configuration files ###
.env
```

7. Run application

## 5 - Creating Entities
1. Work on connecting a One to Many relationship with the Account class and Transaction class.
- First we work on One to Many relationship
- Second we work on Many to Many relationship
- Third we work on One to One relationship

## 6 - Mapping a One to Many Relationship (most common/simple relationship)
1. First we work on One to Many relationship
- One Account can have Many Transactions. Any Transaction we grab should point back to only one Account.
2. Within the Account class (the "One" relationship) we will add an instance variable to hold the list of Transactions, it will hold
```Java
private List<Transaction> transactions = new ArrayList<>();
```
- We need to tell hibernate what the relationship is between the Account and the Transaction. Once we attach the instance variable now we need tell hibernate what that field is. Above the instance variable we'll write
```Java
@OneToMany
```

3. Now within the Transaction class (the "Many" relationship) we will add an instance variable to hold the Account object.
```Java
private Account account;
```
- We need to tell hibernate what the relationship is between the Transaction and the Account. Once we attach the instance variable now we need tell hibernate what that field is. Above the instance variable we'll write
```Java
@ManyToOne
``` 

4. Now that we have those relationships assinged using annotations we'll go back to the Account class and within the `@OneToMany` annotation we'll add `(mappedBy = "account")`, assinging the "one" part of the relationship to `account`, which is the instance variable name within the Transaction class. The annotation will look like this.
```Java
@OneToMany(mappedBy = "account")
```

5. Next we will head over to the Transaction class and above the instance variable `private Account account;` we will assign the `@ManyToOne` annotation. And something odd we ran into as we were running the application, the console spit out `account_account_id` as the column name for `account_id`. That is wrong, so we will add the `@JoinColumn(name="account_id")` above the `private Account account;` instance variable. When we're all said and done it will look like this. Pretty sure the console spit out `account_account_id` because the first "account" is for the class name and then appended to it is the `private Long accountId;` as "account_id".
```Java
@ManyToOne
    @JoinColumn(name="account_id")
    private Account account;
```    

## 7 - Mapping a Many to Many Relationship
1. In this Banking Account we can have a Many To Many relationship between User and Account
- One way of saying it
    - There could be multiple users on an account (Spouse/Partner)
    - And there could be multiple acccounts associated with those multiple users
- Another way of saying it
    - One, or more, users can be associate with multiple bank accounts
    - Any bank account could map back to multiple users
        - With that said this is a Many To Many relationship

- We are also looking at the Parent-Child relationship.
    - Parent or owning side is the User of the account and the account is the child
        - You can't have an account without a user.

2. To assing the Many To Many Relationship we'll start in User class and add the instance variable below.
```Java
private List<Account> accounts = new ArrayList<>();
```
- Above that we'll attach the `@ManyToMany` annotation
- And below that annotation we'll assign the the `JoinTable` name that connects these two tables. That annotaion is below.
    - The `JoinTable` is a combination of the `Parent table name and the Child table name`. In this case the Parent table is called User and the Child table is called Account. So we'll combine those two names, make sure they are lowercase and put an underscore between the two so it's successfully read by MySQL.
```Java
@JoinTable(name="user_account")
```
- Now attached/below the `@JoinTable` we'll add the columns that we are joining.
    - First we start with the column name within the table we are in and then what column name of the Account table do we want to connect to.
        - We want the User's JoinColumn to be `userId` (`user_id` in MySQL) and we want the `inverseJoinColumn` or aka the column name of the Account table we are joining this to, will be `accountId` (`account_id` in MySQL). See example below.
```Java
    @JoinTable(name="user_account", // Table name
        joinColumns = @JoinColumn(name="user_id"), // Parent class column name
        inverseJoinColumns = @JoinColumn(name="account_id")) // Child class column name
```
- Something to remeber, you will only map this out on the Parent class of the relationship. Over on the Child class, aka the Account class we will create a new instance variable to hold the users in the Account class, and above that instance variable we will assign the Many To Many annotation and assign the name to be `accounts`. It will look like this...
```Java
@ManyToMany(mappedBy = "accounts") 
    private List<User> users = new ArrayList<>();
```
- "accounts" is the instance variable name from the User class that is a List. Need to ensure we grab the correct instance variable name so it's being correctly mapped back to the correct location.


## 8 - Mapping a One to One Relationship
1. Start off by creating the Address POJO and add the `@Entity` annotation at the top of the class.
2. The One to One relationship is between the User and the Address
- There is one user for every address and one address for every user.
    - Technically, in the future this could be a One to Many relationship in the future because One person could have multiple addresses.
        - Yet this is a great opportunity to use a One to One relationship so we'll continue building it as such.
- In the Address class we'll add the instance variable for a user, `private User user;`
- And in the User class we'll add the instance variable for an address, `private Address address;`
    - Make sure to add Getters and Setters for each.

3. Add annotations
- In a One to One relationship there isn't technically a Parent Child relationship but assigning those names to the tables could help us figure out what annotaitons to put where.
- Add the `@OneToOne` annotation in the User class above address instance variable.
    - And add `(mappedBy = "user")` becauase we are mapping this to the instance variable, user, inside the Address class.
- Now it's time to add further annotations.
    - Make sure the `@Id` is above the userId instance variable in the Address class to assign that it's the Primary Key.
    - We'll also want to use the `userId` as the Foreign Key as well.
        - To do that we'll add the following above the user instance variable.

- Because the `userId` column is two things at once. It's the Primary Key and the Foreign Key so it's now considered an embeded key.
    - We are using the `mapsId` to solve this problem. Below the `mapsId` annotation we need to assign the column name we're attaching this data to and that would be the userId/user_id colum. If we didnt' add `@JoinColumn` and tell it the correct column name it would default to `user_user_id` and we don't want that.

DID THIS BECAUSE INK DROP WOULD NOT LET ME CONTINUE WRITING THIS DOC WITHOUT ADDING SOMETHING WEIRD LIKE THIS
```text
@OneToOne
@MapsId
@JoinColumn(name = "user_id")
private User user;
```

```Java
@OneToOne
@MapsId
@JoinColumn(name = "user_id")
private User user;
```

---

# Unit 19 ORM Hibernate (Part 2)

## 1 - Intro to Spring Data Jpa
1. Instead of using JDBC (Java Database Connectivity) to manually create our SQL queries we will use Spring Data JPA because there is a lot of "out of the box" queries already made for use. We'll dive deeper into customizing our own later on.
2. Create the `repository` package at the main folder level within the `java` package. This is the same level that you'll already have the `domain` package.
3. Within `repository` create a UserController interface, not class. This interface will extend `JpaRepository` and it will extend 2 things.
- 1. It extends T which stands for Type of domain object it will be working with. In this case, the UserRepository extends the domain object of `User`.
- 2. And ID, stands for Type of Id. When you go to the User class what is the @Id of the User? The @Id is Long, and that'll be what you put in for ID.
  - See below for the visual example
    - Because this is an interface we use `extends`. If this was a class we would use `implements`, rather than `extends`.
```Java
package com.coderscampus.Unit_18_Hibernate_2.web;

import com.coderscampus.Unit_18_Hibernate_2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserController extends JpaRepository<User, Long> {
}
```
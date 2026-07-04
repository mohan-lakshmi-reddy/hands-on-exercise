# Exercise 1: Configuring a Basic Spring Application

A classic **XML-based Spring Core** project — no Spring Boot, no annotations for bean
definitions. This is how Spring was configured before annotation-based config (`@Component`,
`@Service`, etc.) became the norm — understanding this "manual" version makes it much clearer
*why* the annotation-based shortcuts in Spring Boot exist and what they're doing behind the scenes.

## Project Structure

```
LibraryManagement/
├── pom.xml                                          Maven config - only needs spring-context
└── src/main/
    ├── java/com/library/
    │   ├── App.java                                 Main class - starts the Spring container
    │   ├── repository/BookRepository.java           "Data layer" (in-memory list for this exercise)
    │   └── service/BookService.java                 Depends on BookRepository via constructor
    └── resources/
        └── applicationContext.xml                   Bean definitions - THE key file for this exercise
```

## How to Run in IntelliJ

1. `File → Open` → select the `LibraryManagement` folder (the one with `pom.xml`).
2. Let IntelliJ import it as a Maven project (same process as before — wait for
   dependency resolution to complete; this project only needs one small dependency,
   `spring-context`, so it should be much faster than our earlier Spring Boot project).
3. Open `App.java`, click the green ▶ next to `public static void main(...)`, and Run.

## Expected Output

```
Books currently in the library:
 - Effective Java
 - Clean Code
 - Spring in Action

Added: Head First Design Patterns

Books currently in the library:
 - Effective Java
 - Clean Code
 - Spring in Action
 - Head First Design Patterns
```

## What's Actually Happening (Walkthrough)

1. **`App.java` starts the container:**
   ```java
   ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
   ```
   This one line tells Spring: "read this XML file, and build every bean it describes."

2. **Spring reads `applicationContext.xml` and processes it top to bottom:**
   ```xml
   <bean id="bookRepository" class="com.library.repository.BookRepository" />
   ```
   Spring creates a `BookRepository` object (calling its no-args constructor) and stores
   it internally under the name `"bookRepository"`.

   ```xml
   <bean id="bookService" class="com.library.service.BookService">
       <constructor-arg ref="bookRepository" />
   </bean>
   ```
   Spring then creates a `BookService` object — but to do so, it must call `BookService`'s
   constructor, which requires a `BookRepository` parameter. The `<constructor-arg ref="bookRepository" />`
   line tells Spring exactly which bean to pass in: the one it just created above.

3. **Back in `App.java`, we ask for the finished object:**
   ```java
   BookService bookService = (BookService) context.getBean("bookService");
   ```
   By this point, Spring has *already* built the entire object graph — `BookService`
   holding a real, working `BookRepository` inside it. We never wrote `new BookService(...)`
   or `new BookRepository()` anywhere in our own Java code.

## The Interview-Relevant Takeaway

This is **the same Dependency Injection concept** used throughout our bigger Library
Management System (Spring Boot) project — the only difference is *how* we tell Spring
what to wire together:

| | This exercise (XML) | Our Spring Boot project (annotations) |
|---|---|---|
| Declare a bean | `<bean class="...BookRepository" />` | `@Repository` on the class |
| Wire a dependency | `<constructor-arg ref="..." />` | Just declare it as a constructor parameter — Spring auto-detects it |
| Start the container | `new ClassPathXmlApplicationContext(...)` | `SpringApplication.run(...)` |

**If asked "why did Spring move away from XML?"** — XML configuration works, but it's verbose,
error-prone (typos in class names aren't caught until runtime), and separates the configuration
from the code it configures. Annotations put the configuration right next to the class itself,
and Spring Boot's component scanning finds everything automatically — much less to maintain
as a project grows to dozens or hundreds of beans.

## Honesty Note

This project was written and manually checked for correctness, but **not compiled in a live
environment** — this sandbox has no Maven installed and no internet access to fetch the
`spring-context` dependency. Please run it in IntelliJ as described above; if you hit any
error, paste it here and I'll debug it immediately.

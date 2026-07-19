# Hands-on 1: Spring Data JPA - Quick Example — Complete

## Prerequisites (must be running BEFORE you start the app)

1. **MySQL Server 8.0** running locally.
2. Run `country_setup.sql` (included in this project) in MySQL Workbench, or via the
   `mysql` CLI:
   ```
   mysql -u root -p < country_setup.sql
   ```
   This creates the `ormlearn` schema, the `country` table, and inserts India + US.

## Project Structure

```
orm-learn/
├── pom.xml
├── country_setup.sql                                     Run this FIRST, in MySQL
└── src/
    ├── main/java/com/cognizant/ormlearn/
    │   ├── OrmLearnApplication.java                       Main class + test wiring
    │   ├── model/Country.java                              JPA entity
    │   ├── repository/CountryRepository.java               Spring Data JPA repository
    │   └── service/CountryService.java                     getAllCountries()
    ├── main/resources/application.properties               DB + logging config
    └── test/java/com/cognizant/ormlearn/
        └── OrmLearnApplicationTests.java                    Default context-load test
```

## How to Run

1. Open in IntelliJ/Eclipse, let Maven resolve dependencies.
2. Confirm MySQL is running and `country_setup.sql` has been executed.
3. Run `OrmLearnApplication`'s `main()` method.
4. Check the console for:
   ```
   Inside main
   Start
   countries=[Country{code='IN', name='India'}, Country{code='US', name='United States of America'}]
   End
   ```

## ⚠️ One correction I made to the document's sample code

The document's `Country.java` snippet shows:
```java
@Column(name="code")
private String code;
@Column(name="name")
private String name;
```
But the **actual table DDL** given earlier in the same document is:
```sql
CREATE TABLE country(co_code varchar(2) primary key, co_name varchar(50));
```
Notice the real column names are `co_code` and `co_name` — **not** `code` and `name`.
Since `ddl-auto=validate` checks that entity columns match real database columns
*exactly*, using `@Column(name="code")` against a table that actually has a `co_code`
column would fail at startup with a validation error. I corrected this in the actual
`Country.java` file to use `@Column(name="co_code")` and `@Column(name="co_name")`,
matching the real table. Worth remembering: **this exact mismatch (entity column names
not matching real table columns) is a very common real-world bug**, and `ddl-auto=validate`
existing specifically to catch it early is a good thing to mention if asked "why use
validate instead of update in some environments."

---

## SME Walkthrough — the 6 requested points

### 1. `src/main/java` — application code
Contains three packages: `model` (JPA entities), `repository` (Spring Data JPA
interfaces), `service` (business logic) — the same Controller-Service-Repository-style
layering as our bigger Library Management System project (minus a controller here,
since this exercise tests everything directly from `main()`, no REST endpoints yet).

### 2. `src/main/resources` — configuration
Contains `application.properties` — database connection details, Hibernate settings,
and logging configuration. No Java code lives here, only configuration.

### 3. `src/test/java` — test code
Contains the auto-generated `OrmLearnApplicationTests`, with its single `contextLoads()`
test — verifies the whole Spring context (including the database connection) starts up
successfully. If MySQL isn't running or the schema doesn't match, **this test would fail
immediately**, which is a fast way to catch configuration problems in CI before manual testing.

### 4. `OrmLearnApplication.java` — the `main()` method walkthrough
```java
ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
LOGGER.info("Inside main");
countryService = context.getBean(CountryService.class);
testGetAllCountries();
```
Notice this is slightly different from a typical Spring Boot `main()` — we **capture**
the `ApplicationContext` that `SpringApplication.run()` returns (usually people ignore
this return value), specifically so we can then call `context.getBean(CountryService.class)`
and manually pull the `CountryService` bean out of the container, right here in `main()`,
without needing a `@RestController` to trigger it via an HTTP request. This is a handy
technique for quick manual testing/prototyping, though in a real REST API you'd trigger
service methods through a controller instead, as we did in our Library Management System.

### 5. Purpose of `@SpringBootApplication`
Combines `@Configuration` + `@EnableAutoConfiguration` + `@ComponentScan` in one
annotation. Here specifically: `@EnableAutoConfiguration` is what notices
`spring-boot-starter-data-jpa` + the MySQL driver are on the classpath, and
automatically configures a `DataSource`, `EntityManagerFactory`, and transaction
management for you — you never manually wire any of that yourself.

### 6. `pom.xml` walkthrough
- `spring-boot-starter-data-jpa` — Spring Data JPA + Hibernate as its implementation
- `mysql-connector-java` (runtime scope) — the actual JDBC driver that lets Java
  talk to MySQL over the network; `runtime` scope means it's needed to *run* the
  app but not to *compile* your code (your code never directly references driver
  classes — Spring/JDBC finds it automatically at runtime by name)
- `spring-boot-devtools` (optional, runtime) — auto-restart on code changes, dev-only

**Viewing the Dependency Hierarchy:** in Eclipse, open `pom.xml`, click the
"Dependency Hierarchy" tab at the bottom — you'll see `spring-boot-starter-data-jpa`
expand into `hibernate-core`, `spring-orm`, `spring-data-jpa`, and more nested beneath
it — a good visual for understanding how one starter dependency resolves into dozens
of actual jars.

## Honesty Note

Written and manually reviewed for correctness; not compiled/run in this sandbox (no
Maven CLI, no MySQL, no internet access here). Please run `country_setup.sql` in MySQL
first, then run the app in your IDE, and share the console output or any error —
happy to debug from there.

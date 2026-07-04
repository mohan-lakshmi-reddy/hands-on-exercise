# Exercise 4: Creating and Configuring a Maven Project

Sets up a fresh Maven project skeleton with the 3 core Spring dependencies needed
for a classic (pre-Boot) Spring web application, plus explicit Java 1.8 compiler
configuration.

## Project Structure

```
LibraryManagement-Exercise4/
├── pom.xml                                    The main deliverable for this exercise
└── src/main/
    ├── java/com/library/
    │   └── SampleController.java              Minimal proof that spring-webmvc works
    ├── resources/
    └── webapp/WEB-INF/                         (Where web.xml/servlet config would go
                                                  for a full deployable WAR - not required
                                                  just to satisfy this exercise's 3 steps)
```

## Step-by-Step: What Each Part of pom.xml Does

### Step 1 — The project itself
```xml
<groupId>com.library</groupId>
<artifactId>LibraryManagement</artifactId>
<version>1.0.0</version>
<packaging>war</packaging>
```
`packaging` is `war` (Web Application Archive), not `jar` — this is the traditional
packaging format for deploying a Java web app to a servlet container like Tomcat,
before Spring Boot's embedded-server approach became standard.

### Step 2 — The three dependencies

| Dependency | What it actually gives you | Where you've already used the equivalent |
|---|---|---|
| **spring-context** | The IoC container itself — bean creation, Dependency Injection | Exercises 1 & 2 (`ApplicationContext`, `<bean>` tags, DI) |
| **spring-aop** | Aspect-Oriented Programming — inject cross-cutting behavior (logging, security checks, transaction handling) around existing methods, without editing those methods | Our main project's `@Transactional` annotation is actually implemented using Spring AOP behind the scenes! |
| **spring-webmvc** | Handles HTTP requests — `@Controller`, `@RequestMapping`, routing | Our main project's `@RestController` / `@GetMapping` (Spring Boot builds on top of Spring WebMVC) |

**A concrete AOP example, to make it click:** imagine wanting to log "Method X was
called" before *every single* method in every Service class, without pasting a log
line into each one manually. AOP lets you write that logging logic **once**, as an
"aspect," and apply it across many classes automatically — that's the "cross-cutting"
part: it cuts across your normal class structure instead of living inside one class.

### Step 3 — The Maven Compiler Plugin
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
        <source>1.8</source>
        <target>1.8</target>
    </configuration>
</plugin>
```
This tells Maven: "compile this code as Java 8, and make sure the resulting bytecode
is compatible with a Java 8 runtime." Without this, Maven uses its own default Java
version, which might not match what your team has agreed on or what your deployment
server actually runs.

**Worth knowing for the interview:** our main Spring Boot 3 project instead uses
`<java.version>17</java.version>`, because Spring Boot 3 requires Java 17 as a
minimum. Seeing both in your prep is useful — it shows you understand that the
Java version is a deliberate, configurable choice tied to what the rest of your
stack requires, not a fixed rule.

## How to Run/Verify in IntelliJ

1. Open the folder in IntelliJ, let it import as a Maven project.
2. Since this is a `war` project without a configured servlet container, the
   simplest way to confirm it's all wired correctly is:
   - Check that `SampleController.java` shows **no red underlines** on `@Controller`,
     `@GetMapping`, or `@ResponseBody` — if spring-webmvc resolved correctly, these
     will resolve cleanly.
   - Run `mvn compile` (via the Maven panel's Lifecycle → `compile`) and confirm
     it succeeds with 0 errors.
3. To actually *run* this as a live web server, you'd need to either add Tomcat
   as a Maven plugin (`tomcat7-maven-plugin`) or deploy the resulting `.war` to
   a standalone Tomcat server — that's genuinely more setup than this exercise
   asks for, so compiling cleanly is the right bar for "done" here.

## Honesty Note

Written and manually reviewed for correctness; not compiled in this sandbox (no
Maven CLI or internet access here, same limitation as the earlier exercises).
Please compile it in IntelliJ and share any error output if something doesn't
resolve as expected.

# Exercise 2: Implementing Dependency Injection (Setter Injection)

Builds on Exercise 1, but switches from **constructor injection** to **setter injection**.

## Project Structure

```
LibraryManagement-Exercise2/
├── pom.xml
└── src/main/
    ├── java/com/library/
    │   ├── LibraryManagementApplication.java     Main class - tests the DI wiring
    │   ├── repository/BookRepository.java        Unchanged from Exercise 1
    │   └── service/BookService.java              CHANGED: now uses a setter, not a constructor
    └── resources/
        └── applicationContext.xml                CHANGED: uses <property>, not <constructor-arg>
```

## How to Run in IntelliJ

Same process as Exercise 1: open the folder, let Maven import resolve `spring-context`,
then click ▶ next to `main()` in `LibraryManagementApplication.java`.

## Expected Output

```
Dependency Injection successful: BookRepository was injected into BookService.

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

## Constructor Injection vs Setter Injection - the actual difference

**Exercise 1 (constructor injection):**
```java
private final BookRepository bookRepository;

public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
}
```
```xml
<bean id="bookService" class="com.library.service.BookService">
    <constructor-arg ref="bookRepository" />
</bean>
```
Spring builds the object in ONE step: `new BookService(bookRepository)`. The dependency
is required at creation time - you cannot end up with a `BookService` that has a null
`bookRepository`. The field can be `final`, meaning it's guaranteed to never change after
construction.

**Exercise 2 (setter injection):**
```java
private BookRepository bookRepository;   // NOT final - it must be reassignable

public BookService() { }   // no-args constructor

public void setBookRepository(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
}
```
```xml
<bean id="bookService" class="com.library.service.BookService">
    <property name="bookRepository" ref="bookRepository" />
</bean>
```
Spring builds the object in TWO steps: first `new BookService()` (a "bare" object with
`bookRepository` still null), THEN `bookService.setBookRepository(bookRepository)`. The
field can't be `final`, since it's assigned after construction, not inside it.

## Which one should you actually use? (a very likely interview question)

**Constructor injection is generally preferred**, and it's what our main Spring Boot
project (`BookServiceImpl`, `MemberServiceImpl`, `BorrowServiceImpl`) uses throughout.
Reasons:

1. **Guaranteed completeness** - you cannot create a half-wired object. If a dependency
   is missing, the code simply won't compile/construct, catching the mistake immediately
   rather than causing a `NullPointerException` later at runtime.
2. **Immutability** - the field can be `final`, so nobody can accidentally reassign the
   dependency later in the object's lifetime.
3. **Easier to test** - as we saw in `BookServiceTest`, you just pass mocks straight into
   the constructor; no risk of forgetting to call a setter before running a test.

**Setter injection has a narrow, legitimate use case:** *optional* dependencies - things a
class can function without, but will use if provided. Since you can't skip a constructor
parameter, but you CAN simply never call an optional setter, setter injection is the
natural fit there. For *required* dependencies (like ours), constructor injection is the
stronger default.

## Honesty Note

Same as Exercise 1 - written and manually reviewed, but not compiled in this sandbox
(no Maven CLI, no internet access to fetch dependencies here). Run it in IntelliJ and
send me any error output if something doesn't work as expected.

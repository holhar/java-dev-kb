# Pivotal Certified Professional Spring Study Guide

Code examples and references to the topics and questions referenced in the Spring professional certification study 
guide of [VMware Pivotal Labs](https://d1fto35gcfffzn.cloudfront.net/academy/Spring-Professional-Certification-Study-Guide.pdf)

----

## References

* [Spring Framework Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/)

## Topic and question list

### 1 Container, Dependency and IOC

1.1 What is dependency injection and what are the advantages of using it?

1.2 What is an interface and what are the advantages of making use of them in Java?

1.3 What is an ApplicationContext?

1.4 How are you going to create a new instance of an ApplicationContext?

1.5 Can you describe the lifecycle of a Spring Bean in an ApplicationContext?

1.6 How are you going to create an ApplicationContext in an integration test?

1.7 What is the preferred way to close an application context?  Does Spring Boot do this for you?

1.8 Can you describe:
  -- Dependency injection using Java configuration?

  -- Dependency injection using annotations (@Autowired)?

  -- Component scanning, Stereotypes?

  -– Scopes for Spring beans? What is the default scope?

1.9 Are beans lazily or eagerly instantiated by default? How do you alter this behavior?

1.10 What is a property source? How would you use @PropertySource?

1.11 What is a BeanFactoryPostProcessor and what is it used for? When is it invoked?

  -- Why would you define a static @Bean method when creating your own BeanFactoryPostProcessor?

  -- What is a PropertySourcesPlaceholderConfigurer used for?

1.12 What is a BeanPostProcessor and how is it different to a BeanFactoryPostProcessor? What do they do? When are they called?

  -- What is an initialization method and how is it declared on a Spring bean?

  -- What is a destroy method, how is it declared and when is it called?

  -- Consider how you enable JSR-250 annotations like @PostConstruct and @PreDestroy? When/how will they get called?

  -- How else can you define an initialization or destruction method for a Spring bean?

1.13 What does component-scanning do?

1.14 What is the behavior of the annotation @Autowired with regards to field injection, constructor injection and method injection?

1.15 How does the @Qualifier annotation complement the use of @Autowired?

1.16 What is a proxy object and what are the two different types of proxies Spring can create?

  -- What are the limitations of these proxies (per type)?

  -- What is the power of a proxy object and where are the disadvantages?

1.17 What does the @Bean annotation do?

1.18 What is the default bean id if you only use @Bean?  How can you override this?

1.19 Why are you not allowed to annotate a final class with @Configuration

  -- How do @Configuration annotated classes support singleton beans?

  -- Why can’t @Bean methods be final either?

1.20 How do you configure profiles? What are possible use cases where they might be useful?

1.21 Can you use @Bean together with @Profile?

1.22 Can you use @Component together with @Profile?

1.23 How many profiles can you have?

1.24 How do you inject scalar/literal values into Spring beans?

  -- What is @Value used for?

1.25 What is Spring Expression Language (SpEL for short)?

1.26 What is the Environment abstraction in Spring?

1.27 Where can properties in the environment come from – there are many sources for properties – check the documentation if not sure. Spring Boot adds even more.

1.28 What can you reference using SpEL?

1.29 What is the difference between $ and # in @Value expressions?

---

### 2 Aspect-Oriented Programming (AOP)

2.1 What is the concept of AOP? Which problem does it solve? What is a cross cutting concern?

  -- Name three typical cross cutting concerns.
  -- What two problems arise if you don’t solve a cross cutting concern via AOP?

2.2 What is a pointcut, a join point, an advice, an aspect, weaving?

2.3 How does Spring solve (implement) a cross cutting concern?

2.4 Which are the limitations of the two proxy-types?

  -- What visibility must Spring bean methods have to be proxied using Spring AOP?

2.5 How many advice types does Spring support?  Can you name each one?

  -- What are they used for?

  -- Which two advices can you use if you would like to try and catch exceptions?

2.6 If shown pointcut expressions, would you understand them?

  -- For example, in the course we matched getter methods on Spring Beans, what would be the correct pointcut expression to match both getter and setter methods?

2.7 What is the JoinPoint argument used for?

2.8 What is a ProceedingJoinPoint? Which advice type is it used with?

---

### 3 Data Management: JDBC, Transactions

3.1 What is the difference between checked and unchecked exceptions?

  -- Why does Spring prefer unchecked exceptions?

  -- What is the Spring data access exception hierarchy?

3.2 How do you configure a DataSource in Spring?

3.3 What is the Template design pattern and what is the JDBC template?

3.4 What is a callback? What are the JdbcTemplate callback interfaces that can be used with queries? What is each used for? (You would not have to remember the interface names in the exam, but you should know what they do if you see them in a code sample).

3.5 Can you execute a plain SQL statement with the JDBC template?

3.6 When does the JDBC template acquire (and release) a connection, for every method called or once per template? Why?

3.7 How does the JdbcTemplate support queries? How does it return objects and lists/maps of objects?

3.8 What is a transaction? What is the difference between a local and a global transaction?

3.9 Is a transaction a cross cutting concern? How is it implemented by Spring?

3.10 How are you going to define a transaction in Spring?

  -- What does @Transactional do? What is the PlatformTransactionManager?

3.11 Is the JDBC template able to participate in an existing transaction?

3.12 What is @EnableTransactionManagement for?

3.13 How does transaction propagation work?

3.14 What happens if one @Transactional annotated method is calling another @Transactional annotated method inside a same object instance?

3.15 Where can the @Transactional annotation be used? What is a typical usage if you put it at class level?

3.16 What does declarative transaction management mean?

3.17 What is the default rollback policy? How can you override it?

3.18 What is the default rollback policy in a JUnit test, when you use the @RunWith(SpringJUnit4ClassRunner.class) in JUnit 4 or  @ExtendWith(SpringExtension. class) in JUnit 5, and annotate your @Test annotated method with @Transactional?

3.19 Are you able to participate in a given transaction in Spring while working with JPA?

3.20 Which PlatformTransactionManager(s) can you use with JPA?

3.21 What do you have to configure to use JPA with Spring?  How does Spring Boot make this easier?

---

### 4 Spring Data JPA

4.1 What is a Spring Data Repository interface?

4.2 How do you define a Spring Data Repository interface? Why is it an interface and not a class?

4.3 What is the naming convention for finder methods in a Spring Data Repository interface?

4.4 How are Spring Data repositories implemented by Spring at runtime?

4.5 What is @Query used for?

---

### 5 Spring MVC and The Web Layer

5.1 What is the @Controller annotation used for?

5.2 How is an incoming request mapped to a controller and mapped to a method?

5.3 What is the difference between @RequestMapping and @GetMapping?

5.4 What is @RequestParam used for?

5.5 What are the differences between @RequestParam and @PathVariable?

5.6 What are the ready-to-use argument types you can use in a controller method?

5.7 What are some of the valid return types of a controller method?

---

### 6 REST

6.1 What does REST stand for?

6.2 What is a resource?

6.3 Is REST secure? What can you do to secure it?

6.4 Is REST scalable and/or interoperable?

6.5 Which HTTP methods does REST use?

6.6 What is an HttpMessageConverter?

6.7 Is @Controller a stereotype? Is @RestController a stereotype?

  -- What is a stereotype annotation?  What does that mean?

6.8 What is the difference between @Controller and @RestController?

6.9 When do you need to use @ResponseBody?

6.10 What are the HTTP status return codes for a successful GET, POST, PUT or DELETE operation?

6.11 When do you need to use @ResponseStatus?

6.12 Where do you need to use @ResponseBody? What about @RequestBody?

6.13 If you saw example Controller code, would you understand what it is doing?  Could you tell if it was annotated correctly?

6.14 What Spring Boot starter would you use for a Spring REST application?

6.15 If you saw an example using RestTemplate, would you understand what it is doing?

---

### 7 Security

7.1 What are authentication and authorization? Which must come first?

7.2 Is security a cross cutting concern? How is it implemented internally?

7.3 What is the delegating filter proxy?

7.4 What is the security filter chain?

7.5 What is a security context?

7.6 What does the ** pattern in an antMatcher or mvcMatcher do?

7.7 Why is the usage of mvcMatcher recommended over antMatcher?

7.8 Does Spring Security support password encoding?

7.9 Why do you need method security? What type of object is typically secured at the method level (think of its purpose not its Java type).

7.10 What do @PreAuthorized and @RolesAllowed do? What is the difference between them?

7.11 How are these annotations implemented?

7.12 In which security annotation, are you allowed to use SpEL?

---

### 8 Testing

8.1 What type of tests typically use Spring?

8.2 How can you create a shared application context in a JUnit integration test?

8.3 When and where do you use @Transactional in testing?

8.4 How are mock frameworks such as Mockito or EasyMock used?

8.5 How is @ContextConfiguration used?

8.6 How does Spring Boot simplify writing tests?

8.7 What does @SpringBootTest do?  How does it interact with @SpringBootApplication and @SpringBootConfiguration?

---

### 9 Spring Boot Intro

9.1 What is Spring Boot?

9.2 What are the advantages of using Spring Boot?

9.3 What things affect what Spring Boot sets up?

9.4 What is a Spring Boot starter?  Why is it useful?

9.5 Spring Boot supports both properties and YML files.  Would you recognize and understand them if you saw them?

9.6 Can you control logging with Spring Boot? How?

9.7 Where does Spring Boot look for application.properties file by default?

9.8 How do you define profile specific property files?

9.9 How do you access the properties defined in the property files?

9.10 What properties do you have to define in order to configure external MySQL?

9.11 How do you configure default schema and initial data?

9.12 What is a fat jar? How is it different from the original jar?

9.13 What embedded containers does Spring Boot support?

---

### 10 Spring Boot Auto Configuration

10.1 How does Spring Boot know what to configure?

10.2 What does @EnableAutoConfiguration do?

10.3 What does @SpringBootApplication do?

10.4 Does Spring Boot do component scanning? Where does it look by default?

10.5 How are DataSource and JdbcTemplate auto-configured?

10.6 What is spring.factories file for?

10.7 How do you customize Spring Boot auto-configuration?

10.8 What are the examples of @Conditional annotations? How are they used?

---

### 11 Spring Boot Actuator

11.1 What value does Spring Boot Actuator provide?

11.2 What are the two protocols you can use to access actuator endpoints?

11.3 What are the actuator endpoints that are provided out of the box?

11.4 What is info endpoint for? How do you supply data?

11.5 How do you change logging level of a package using loggers endpoint?

11.6 How do you access an endpoint using a tag?

11.7 What is metrics for?

11.8 How do you create a custom metric?

11.9 What is Health Indicator?

11.10 What are the Health Indicators that are provided out of the box?

11.11 What is the Health Indicator status?

11.12 What are the Health Indicator statuses that are provided out of the box?

11.13 How do you change the Health Indicator status severity order?

11.14 Why do you want to leverage 3rd-party external monitoring system?

---

### 12 Spring Boot Testing

12.1 When do you want to use @SpringBootTest annotation?

12.2 What does @SpringBootTest auto-configure?

12.3 What dependencies does spring-boot-starter-test bring to the classpath?

12.4 How do you perform integration testing with @SpringBootTest for a web application?

12.5 When do you want to use @WebMvcTest? What does it auto-configure?

12.6 What are the differences between @MockBean and @Mock?

12.7 When do you want @DataJpaTest for? What does it auto-configure?

WHAT
=========

This application is a user repository. You can create, update, delete and get all users shortly is a CRUD application. 
Application uses this technologies ;

- Spring MVC (Web Framework)
- Mongo DB (Database)
- Thymeleaf (Template Engine)
- JUnit (Test Framework)
- Mockito (Mock Framework for tests)
- Log4J (Logging library)
- ReCaptcha (Captcha library)

Application have 5 packages. These ;

- controller
- dao
- entities
- services
- util

<br />
## Controller

Controller has restful end points for requests on application. For this example only UserController in controller package. 

UserController has 5 methods ;
- index
- save
- delete
- update

#### index
 Path("/"). When enter the site you seen this method's response. This method asked to UserService findAll and write the result to response. You will see all users on site.
 
#### save
Path("/user"). This method's request should have body. This method save user from incoming user in request body. If captcha is worong http response status 406 and return JSON response for information else user will be save,response status be 201 and return JSON response for information.

#### delete
Path("/user/{id}"). This method delete user by incoming id from the url path. Method calls UserService delete method for delete user.

#### update
Path("/user/update"). This method update user from incoming user in request body. User will be updated,response status be 200 and return JSON response for information.

 <br />
## DAO

DAO is mean data access object in other words dao do operations on your database like CRUD. For this application every DAO has interface for dependency injection and inner packages for implementations. This applications has impl.mongo packages for Mongo DB implementations. DAOs interface can be extend GenericCRUD for CRUD interface and DAO class should be extend MongoDaoSupport class. MongoDaoSupport provide mongoDB operations.

<br />
## Entities
Entities are Models. Entity classes should be extend Model class.

<br />
## Services
Services are middleware between DAO and Controller. Services are units where logic is implemented in.

<br />
## Template Engine

Thymeleaf is template engine this application. Thymeleaf is better then JSP or other template engines. The engine is very powerful, useful also popular. This is why i choose it.

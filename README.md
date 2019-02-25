# Summary
The Project Open Movies Information is a self-motivated project as a demonstration of my back-end skills.
This A-to-Z project is developed independently from scratch as well as it will be constantly improving.
The RESTful API service is temporarily deployed on Heroku.

You also can visit GitHub to view Open Movies Information front-end project https://github.com/weican/open-movies-information

# The Infrastructures
The following picture is an overview of the infrastructures of this website:
![image](https://drive.google.com/uc?export=view&id=19qUgN2UUahzy77IrMhpZnbTCawIq7F2H)

# Requirements
### Java
- [Spring Boot](http://spring.io/projects/spring-boot) makes it easy to create stand-alone, production-grade Spring based Applications.
- [Spring Security](https://spring.io/projects/spring-security) is a powerful and highly customizable authentication and access-control framework.
- [Mybatis](http://www.mybatis.org/mybatis-3/index.html) is a first class persistence framework with support for custom SQL, stored procedures and advanced mappings. 
- [MySQL](https://www.mysql.com/) (Database)
- [JWT](https://jwt.io/) is an open standard (RFC 7519) that defines a compact and self-contained way for securely transmitting information between parties as a JSON object. 

# Features
- Automatic copying movies data and extracting the latest movies information from TMDb server into our database by RESTful API
- Using JSON Web Token(JWT) with Spring Security for the authorized users to renew our database
- Providing movies information and user details by RESTful API
### APIs Overview
List of movies:
```
GET https://open-movies-backend.herokuapp.com/public/getAll
```
List of genres:
```
GET https://open-movies-backend.herokuapp.com/public/getGenre
```


# Demo
- **Please view the interactive charts on a computer for optimal experience.**
👉 https://weican.github.io/open-movies-information/  
![image](https://drive.google.com/uc?export=view&id=1HHSvsPx8FUkx1cQ0Yg3QPGaB0Bl3QwGa)


# Declaration and Licence
This project is maintained by Wales Chang and under MIT license, and the repository is hosted for the demonstration purpose. 

🚨 Please use it in your own risks. The author is not responsible or obligated to any damage or lost if you decide to use any part of code hosted from this repository.

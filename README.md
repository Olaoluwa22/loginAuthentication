SIMPLE LOGIN AUTHENTICATION API

This is a simple Login Authentication API built with Java, Springboot and Spring Security.

User Data is persisted using MySql database.

It provides endpoints for user registration, login, and authentication.

Features

-Create a User:

The user will enter personal details such as email,
password and role(user/admin) for an initial profile set up.

-User login with email and password: 

The user is required to input their username(email) and password
 for authentication and authorization.

-DaoAuthentication Provider: 

Spring security will use DaoAuthenticationProvider to get the 
UserDetails and also encode the password using BCryptPasswordEncoder.

-User Authorization: 

Based on user authority/role, user is allowed to access some endpoints and 
not allowed to access some endpoints.

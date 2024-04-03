Login Authentication API with Java, Spring Boot, and Spring Security

This is a simple Login Authentication API built with Java, Spring Boot, and Spring Security. It securely manages user authentication and authorization processes, with user data persisted in a MySQL database.

Endpoints Provided:
User Registration: Endpoint ("/create-user") for registering potential users, requiring email, password, and role information.

Login: Endpoints ("/user-dashboard" & "/admin-dashboard") for accessing user or admin dashboards, requiring registered email and password credentials.

Logout: Endpoint ("/logout") for securely logging out from the application.

Forget Password: Endpoint("/forget-password") for resetting forgotten passwords, requiring the user's email to generate a new password.

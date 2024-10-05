# EloPong

## Project Overview
**EloPong** is an Elo rating system designed to track game results and calculate player Elo ratings. While initially created for Beerpong, it can be adapted to other use cases. Users play matches, and based on the results, their Elo scores are updated. The system includes user management with JWT-based authentication for secure registration and login. The project follows a modern web architecture, with a Spring Boot backend and an Angular frontend.

## Technologies Used
- **Backend:** Spring Boot (3-layer architecture with JPA, Lombok, and Mapstruct)
- **Database:** PostgreSQL
- **Frontend:** Angular
- **Security:** JWT (JSON Web Token) for authentication and authorization

## Current Project Status
The project is still in development, but several key backend functionalities have already been implemented:

- **User Registration and Login:** Fully functional with JWT-based authentication.
- **Game Storage and Elo Rating Calculation:** Games are recorded, and player Elo ratings are updated based on results.

### Frontend Status
Currently, users can **register** and **log in**. Other functionalities, like viewing and editing the profile, displaying game history and Elo ratings, are in progress.

## Planned Features
- **Leaderboard:** An Overview of the Elo-Points of all the players.
- **Game Overview:** A graphical representation of played games and a game history view.
- **User Profiles:** Each user will have an editable profile.
- **Admin Area:** A section for admins to manage users.
- **Password Reset:** Functionality to allow users to reset forgotten passwords.
- **Tournament Bracket:** Future feature allowing automatic tournament bracket generation and management.
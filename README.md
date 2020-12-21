# Birthday Reminder

A tool to remind you of important birthdays

API Deployed on Heroku: [https://anniversary-reminder.herokuapp.com](https://anniversary-reminder.herokuapp.com/)

# Installation

- JDK 15
- Gradle

# API

- GET `/count-days?from=2020-10-01&to=2021-01-01`
  Nombre de jours entre 2 dates
- GET `/reminders`
  Liste des reminders
  
# Database

- H2
- jooq

# DB Seed - Shop Compare

Table of contents
---
- [General info](#general-info)
- [Setup](#setup)
<br/>

General info
---
**Shop compare** is a web application that provides prices comparison for different appliances and electronic
devices. The main goal of this application is to enable customers find their required product at the best price available at the
market. <br/>

**DB seed** is used to perform initializing of the DB schema - creating the tables and also perform seeding data in some of the 
tables such as inserting predefined shops and categories.

The DB seed application utilizes Flyway database-migration tool to perform schema changes and migrations in the database.
Flyway updates a database from one version to the next using migrations and migrations could be SQL based or Java based for
more complicated database queries. <br/>
We have 2 types of migrations in the DB seed application: 
- Versioned migrations  
  - Usually, the migrations for creating the tables are versioned since they need to be performed once.
- Repeatable migrations 
  - Usually, we use the repeatable migrations to seed data in the tables. They don't have a version and are performed on each db seed execution when a change is detected in the repeatable migration.

Setup
---
- Make sure you have JDK17 installed
- Enable dev profile for local setup
- Execute mvn clean install
- Start the Db Seed Application
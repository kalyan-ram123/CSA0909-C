# Task 3 — Generic DAO pattern

Defines Dao<T,K> and a JDBC StudentDao with insert, findById and findAll. It separates SQL/data mapping from the application layer.

Requires MySQL Connector/J and DB_URL, DB_USER, DB_PASSWORD. Create a database first. Compile with the Connector/J JAR on the classpath. The example creates the student table when run.
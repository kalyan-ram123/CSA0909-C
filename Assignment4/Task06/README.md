# Task 6 – Campus Event Registration with JDBC

**Objective:** Build a Swing registration interface connected to MySQL using JDBC.

The application inserts registrations, filters the table by event, and performs database operations in SwingWorker background tasks. A unique database key prevents duplicate registrations for the same student and event.

## Requirements
- JDK 8+
- MySQL Server
- MySQL Connector/J

## Database setup
Run the SQL in `schema.sql` using MySQL Workbench or the MySQL client.

Configure environment variables:
- `DB_URL`: default `jdbc:mysql://localhost:3306/campus_events`
- `DB_USER`: default `root`
- `DB_PASSWORD`: default empty

## Compile and run (Windows)
Place the Connector/J JAR in this folder, then:
```powershell
javac -cp ".;mysql-connector-j.jar" task6.java
java -cp ".;mysql-connector-j.jar" task6
```
On macOS/Linux use `:` instead of `;` in the classpath.

Configure your own database credentials securely; do not commit passwords. Add screenshots for successful registration, invalid input, duplicate registration, and filtered results after testing locally.

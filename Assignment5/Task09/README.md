# Task 9 — CSV migration and database metadata

Reads pass IDs from a CSV/text file, validates empty rows, inserts valid IDs using a JDBC batch in one transaction, and reports database/driver metadata.

Create the bus_pass table using Task08/schema.sql. Configure DB_URL, DB_USER and DB_PASSWORD and include Connector/J. Run `javac Task9.java` then `java Task9 passes.csv`. For a real CSV with multiple columns or quoted values, use a CSV parser and validate every column before insertion.
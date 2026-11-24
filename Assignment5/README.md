# Assignment 5 — Java I/O, JDBC and Database Systems

This folder contains independent implementations and study notes for all nine tasks based on the topics in the reference assignment.

**Reference:** https://github.com/rishi2338/CSA0909-1911260190-ASSIGNMENT5

## Tasks
1. File formats and pass records (text and binary I/O)
2. Buffered versus unbuffered file reading
3. Generic DAO design with JDBC
4. Java object serialization and transient fields
5. JDBC transactions and safe parameterized queries
6. Pass file validation and lookup
7. Safe handling of untrusted login input
8. JDBC batch processing and rollback
9. CSV migration and database metadata

## Requirements
- JDK 17+
- MySQL 8+ and MySQL Connector/J for JDBC tasks (3, 5, 7, 8, 9)
- Set `DB_URL`, `DB_USER`, and `DB_PASSWORD` environment variables. Never commit real credentials.

## Compile
For a task file: `javac TaskN.java`, then `java TaskN`. JDBC files additionally need the Connector/J JAR on the classpath. Read each task note before running.

These are original learning implementations, not a verbatim copy of another student's source. Complete any written/pseudocode portions required by your instructor and run the programs in your own environment; benchmark results are intentionally not fabricated.
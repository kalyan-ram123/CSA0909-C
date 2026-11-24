# Task 8 — Batch processing and rollback

Shows JDBC batch inserts inside a transaction. If insertion fails, the transaction is rolled back. The included schema creates a minimal bus_pass table for the demonstration.

Run schema.sql in MySQL, configure DB_URL/DB_USER/DB_PASSWORD and add Connector/J to the classpath. Compare Statement, PreparedStatement and batch timings locally; repeat measurements and report medians rather than inventing benchmark values.
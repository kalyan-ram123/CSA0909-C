# Task 7 — Adversarial input and PreparedStatement

Demonstrates safe parameter binding for login queries. A user-provided value remains a value rather than changing SQL syntax. Do not test against systems or accounts you do not own or have permission to use.

Requires a local users table with username and password_hash columns, MySQL Connector/J and DB environment variables. Use hashed passwords and test malformed inputs such as empty strings and unexpected characters.
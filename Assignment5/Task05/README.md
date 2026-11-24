# Task 5 — JDBC transactions and safe login

Uses one JDBC connection for a wallet transfer, committing only when both updates succeed and rolling back on failure. The debit checks the available balance. Login uses PreparedStatement parameters.

Requires wallet(wallet_id,balance) and users(username,password_hash) tables plus Connector/J. Store password hashes created with a suitable password-hashing algorithm; never store plaintext passwords. Configure DB_URL, DB_USER and DB_PASSWORD.
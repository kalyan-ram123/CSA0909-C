# Task 4 — Serialization

Serializes a Hostel → Room → Resident object graph. The password and live database connection are transient and therefore are not restored. Never store real passwords in serialized files; use a protected credential store and a strong password hash.

Run: `javac Task4.java` then `java Task4`. Keep serialVersionUID stable for compatible class evolution. JSON/CSV are more portable but require explicit mapping and schema handling.
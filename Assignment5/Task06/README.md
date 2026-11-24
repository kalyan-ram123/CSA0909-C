# Task 6 — Pass file store and validation

Parses five-field CSV-style pass records, searches by ID and skips malformed rows while reporting their line numbers.

Run: `javac Task6.java` then `java Task6`. Tests include valid, missing and damaged records. For production CSV with quoted commas, use a CSV library rather than String.split. A linear lookup is O(n); an index can improve repeated lookups.
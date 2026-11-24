# Task 1 — File format and record storage

Demonstrates a seven-field pass record stored as UTF-8 delimited text and as a binary DataOutputStream record. It supports adding and finding a pass.

Run: `javac Task1.java` then `java Task1`.

Design notes: validate field count and data types; skip damaged records; distinguish a missing file from an empty file. For reliable replacement writes, write to a temporary file and move it into place. RandomAccessFile can seek directly only when records have fixed size or a separate index exists.
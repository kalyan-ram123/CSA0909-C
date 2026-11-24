# Task 2 — Buffered I/O

Compares byte-at-a-time reading with an 8 KiB BufferedInputStream and counts newline characters.

Run: `javac Task2.java` then `java Task2`; optionally pass a file path. Timing depends on hardware, file cache and JVM warm-up, so no fixed result is claimed. Streaming uses constant-sized buffer memory; readAllLines stores the whole file and is less suitable for large files.
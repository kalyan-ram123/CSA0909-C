# Task 5 – Synchronization

**Objective:** Use a synchronized method to protect shared data from concurrent updates.

Multiple customer threads request seats from one shared booking object. The synchronized reserve() method ensures a seat is counted only once.

**Run from repository root:**
```bash
javac Assignment3/Task05/Task5.java
java -cp Assignment3/Task05 Task5
```
The final count should be 10 unique seats.

# Task 6 – Producer-Consumer Using wait() and notifyAll()

**Objective:** Implement communication between threads using a bounded buffer.

One producer inserts the numbers 1 through 20 into a shared buffer. Two consumers remove values. The put() and take() methods use wait() and notifyAll() to coordinate access.

**Run from repository root:**
```bash
javac Assignment3/Task06/Task6.java
java -cp Assignment3/Task06 Task6
```
The total of all consumed values should be 210.

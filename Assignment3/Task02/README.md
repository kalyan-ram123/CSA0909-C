# Task 2 – Custom Exceptions and Exception Chaining

**Objective:** Create checked exceptions and preserve the original cause when an exception is wrapped.

The storage example accepts files up to a sample size limit. An oversized file causes an IOException, which is wrapped in a custom UploadException.

**Run from repository root:**
```bash
javac Assignment3/Task02/Task2.java
java -cp Assignment3/Task02 Task2
```

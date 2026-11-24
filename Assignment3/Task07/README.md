# Task 7 – Concurrent Mark Processing

**Objective:** Use ExecutorService, Callable, Future, and exception handling.

The program prepares sample mark files and submits ten file-processing jobs to a fixed thread pool. Missing files are reported as failed jobs while other jobs continue.

**Run from repository root:**
```bash
javac Assignment3/Task07/Task7.java
java -cp Assignment3/Task07 Task7
```
Sample input files are created in the `student-marks` directory. The summary reports successful and failed jobs.

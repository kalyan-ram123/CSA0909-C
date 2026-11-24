import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Task7 {
    static class MarkProcessor implements Callable<String> {
        private final Path markFile;

        MarkProcessor(Path markFile) {
            this.markFile = markFile;
        }

        @Override
        public String call() throws Exception {
            if (Files.notExists(markFile)) {
                throw new IOException("Missing file: " + markFile.getFileName());
            }
            int mark = Integer.parseInt(Files.readString(markFile).trim());
            if (mark < 0 || mark > 100) {
                throw new IllegalArgumentException("Mark must be between 0 and 100.");
            }
            return markFile.getFileName() + " : " + (mark >= 40 ? "PASS" : "FAIL");
        }
    }

    public static void main(String[] args) {
        Path folder = Path.of("student-marks");
        try {
            Files.createDirectories(folder);
            for (int i = 1; i <= 8; i++) {
                Path file = folder.resolve("mark" + i + ".txt");
                if (Files.notExists(file)) {
                    Files.writeString(file, Integer.toString(30 + i * 8));
                }
            }
        } catch (IOException ex) {
            System.out.println("Unable to prepare sample files: " + ex.getMessage());
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<String>> jobs = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            jobs.add(executor.submit(
                    new MarkProcessor(folder.resolve("mark" + i + ".txt"))));
        }

        int completed = 0;
        int failed = 0;
        try {
            for (Future<String> job : jobs) {
                try {
                    System.out.println(job.get());
                    completed++;
                } catch (ExecutionException ex) {
                    System.out.println("Task error: " + ex.getCause().getMessage());
                    failed++;
                }
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread interrupted.");
        } finally {
            executor.shutdown();
        }

        System.out.println("Summary: " + completed + " completed, " + failed + " failed.");
    }
}

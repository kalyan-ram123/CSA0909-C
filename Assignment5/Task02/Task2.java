import java.io.*;
import java.nio.file.*;

public class Task2 {
    static long countPlain(Path p) throws IOException {
        long lines = 0;
        try (InputStream in = Files.newInputStream(p)) {
            int b; while ((b = in.read()) != -1) if (b == '\\n') lines++;
        }
        return lines;
    }
    static long countBuffered(Path p) throws IOException {
        long lines = 0;
        try (InputStream in = new BufferedInputStream(Files.newInputStream(p))) {
            byte[] buffer = new byte[8192]; int n;
            while ((n = in.read(buffer)) != -1)
                for (int i=0; i<n; i++) if (buffer[i] == '\\n') lines++;
        }
        return lines;
    }
    public static void main(String[] args) throws Exception {
        Path p = args.length == 0 ? Path.of("sample.txt") : Path.of(args[0]);
        if (!Files.exists(p)) Files.writeString(p, "First line\nSecond line\nThird line\n");
        long start = System.nanoTime(); long a = countPlain(p); long plain = System.nanoTime()-start;
        start = System.nanoTime(); long b = countBuffered(p); long buffered = System.nanoTime()-start;
        System.out.println("Unbuffered lines: " + a + " time(ns): " + plain);
        System.out.println("Buffered lines: " + b + " time(ns): " + buffered);
    }
}
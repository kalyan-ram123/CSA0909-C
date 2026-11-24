import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Task3 {
    public static void main(String[] args) {
        Path inputFile = Path.of("sales.txt");
        Path reportFile = Path.of("sales-report.txt");

        try {
            if (Files.notExists(inputFile)) {
                Files.write(inputFile, List.of("North,1200", "South,900", "East,1450"));
            }

            int total = 0;
            try (BufferedReader reader = Files.newBufferedReader(inputFile);
                 BufferedWriter writer = Files.newBufferedWriter(reportFile)) {
                writer.write("SALES SUMMARY");
                writer.newLine();

                String record;
                while ((record = reader.readLine()) != null) {
                    String[] fields = record.split(",");
                    if (fields.length != 2) {
                        throw new IOException("Incorrect sales record: " + record);
                    }
                    int amount = Integer.parseInt(fields[1].trim());
                    total += amount;
                    writer.write(fields[0].trim() + " : " + amount);
                    writer.newLine();
                }

                writer.write("TOTAL : " + total);
                writer.newLine();
            }
            System.out.println("Report saved. Total sales = " + total);
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Could not create report: " + ex.getMessage());
        }
    }
}

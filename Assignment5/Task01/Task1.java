import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class Task1 {
    record Pass(String id, String regNo, String name, int route, String stop, String validity, double amount) {}
    static final Path TEXT = Path.of("passes.txt"), BINARY = Path.of("passes.dat");
    static String escape(String s) { return s.replace("\\", "\\\\").replace("|", "\\|"); }
    static void add(Pass p) throws IOException {
        String line = String.join("|", escape(p.id()), escape(p.regNo()), escape(p.name()),
                Integer.toString(p.route()), escape(p.stop()), escape(p.validity()), Double.toString(p.amount()));
        Files.writeString(TEXT, line + System.lineSeparator(), StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(
                Files.newOutputStream(BINARY, StandardOpenOption.CREATE, StandardOpenOption.APPEND)))) {
            out.writeUTF(p.id()); out.writeUTF(p.regNo()); out.writeUTF(p.name());
            out.writeInt(p.route()); out.writeUTF(p.stop()); out.writeUTF(p.validity()); out.writeDouble(p.amount());
        }
    }
    static Pass find(String id) throws IOException {
        if (!Files.exists(TEXT)) return null;
        for (String line : Files.readAllLines(TEXT, StandardCharsets.UTF_8)) {
            String[] f = line.split("(?<!\\\\)\\|", -1);
            if (f.length == 7 && f[0].equals(id))
                try { return new Pass(f[0], f[1], f[2], Integer.parseInt(f[3]), f[4], f[5], Double.parseDouble(f[6])); }
                catch (NumberFormatException ignored) { }
        }
        return null;
    }
    public static void main(String[] args) throws Exception {
        Pass p = new Pass("P101","REG101","Anu",12,"Central","2027-03-31",850.0);
        add(p);
        System.out.println("Saved: " + p);
        System.out.println("Found: " + find("P101"));
    }
}
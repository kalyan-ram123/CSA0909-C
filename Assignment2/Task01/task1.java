public class Task1 {
    static String usingString(String[] fields) {
        String result = "";
        for (String field : fields) result += field + " | ";
        return result;
    }

    static String usingBuilder(String[] fields) {
        StringBuilder result = new StringBuilder();
        for (String field : fields) result.append(field).append(" | ");
        return result.toString();
    }

    static String usingBuffer(String[] fields) {
        StringBuffer result = new StringBuffer();
        for (String field : fields) result.append(field).append(" | ");
        return result.toString();
    }

    static void measure(String label, java.util.function.Supplier<String> task) {
        long start = System.nanoTime();
        String value = task.get();
        long elapsed = System.nanoTime() - start;
        System.out.println(label + ": " + elapsed + " ns");
        System.out.println("Sample: " + value);
    }

    public static void main(String[] args) {
        String[] ticket = {"Ticket: 1024", "Passenger: Arun", "Train: Chennai Express",
                "From: Chennai", "To: Bengaluru", "Seat: B12-18"};
        measure("String", () -> usingString(ticket));
        measure("StringBuilder", () -> usingBuilder(ticket));
        measure("StringBuffer", () -> usingBuffer(ticket));
    }
}

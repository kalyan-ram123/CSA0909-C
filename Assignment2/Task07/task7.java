public class Task7 {
    static String concatenate(String[] fields) {
        String result = "";
        for (String field : fields) result += field;
        return result;
    }

    static String build(String[] fields) {
        StringBuilder result = new StringBuilder();
        for (String field : fields) result.append(field);
        return result.toString();
    }

    public static void main(String[] args) {
        String[] fields = new String[500];
        for (int i = 0; i < fields.length; i++) fields[i] = "Field" + i + ";";

        long start = System.nanoTime();
        String oldResult = concatenate(fields);
        long stringTime = System.nanoTime() - start;

        start = System.nanoTime();
        String newResult = build(fields);
        long builderTime = System.nanoTime() - start;

        System.out.println("String time: " + stringTime + " ns");
        System.out.println("StringBuilder time: " + builderTime + " ns");
        System.out.println("Outputs match: " + oldResult.equals(newResult));
        System.out.println("Output length: " + newResult.length());
    }
}

import java.nio.file.*;
import java.util.*;

public class Task6 {
    record Pass(String id,String regNo,String name,int route,double amount) {}
    static Pass parse(String line) {
        String[] f=line.split(",",-1);
        if(f.length!=5) return null;
        try { return new Pass(f[0].trim(),f[1].trim(),f[2].trim(),
                Integer.parseInt(f[3].trim()),Double.parseDouble(f[4].trim())); }
        catch(NumberFormatException e){return null;}
    }
    static Pass find(Path file,String id) throws Exception {
        int lineNo=0;
        for(String line:Files.readAllLines(file)) {
            lineNo++; Pass p=parse(line);
            if(p==null){System.out.println("Skipping damaged line "+lineNo);continue;}
            if(p.id().equals(id)) return p;
        }
        return null;
    }
    public static void main(String[] args) throws Exception {
        Path p=Path.of("passes.csv");
        if(!Files.exists(p)) Files.writeString(p,"P1001,R101,Meena,4,500\nP1002,R102,Bala,5,650\nBAD,ROW\n");
        System.out.println("Lookup: "+find(p,"P1002"));
        System.out.println("Missing: "+find(p,"P9999"));
    }
}
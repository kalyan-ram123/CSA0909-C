import java.io.*;
import java.util.*;

public class Task4 {
    static class Resident implements Serializable {
        private static final long serialVersionUID = 1L;
        String registerNo, name;
        transient String password;
        Resident(String r,String n,String p){registerNo=r;name=n;password=p;}
        public String toString(){return registerNo+" "+name+" password="+password;}
    }
    static class Room implements Serializable {
        private static final long serialVersionUID = 1L;
        int roomNo; List<Resident> residents=new ArrayList<>();
        Room(int n){roomNo=n;}
    }
    static class Hostel implements Serializable {
        private static final long serialVersionUID = 1L;
        List<Room> rooms=new ArrayList<>();
        transient Object liveDatabaseConnection;
    }
    public static void main(String[] args) throws Exception {
        Hostel h=new Hostel(); Room r=new Room(101);
        r.residents.add(new Resident("REG101","Asha","not-saved")); h.rooms.add(r);
        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("hostel.ser"))){out.writeObject(h);}
        try(ObjectInputStream in=new ObjectInputStream(new FileInputStream("hostel.ser"))){
            Hostel restored=(Hostel)in.readObject();
            System.out.println("Rooms restored: "+restored.rooms.size());
            System.out.println(restored.rooms.get(0).residents.get(0));
        }
    }
}
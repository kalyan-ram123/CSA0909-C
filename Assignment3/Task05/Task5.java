public class Task5 {
    private final boolean[] reservedSeats;
    private int totalBooked;

    Task5(int seatCount) {
        reservedSeats = new boolean[seatCount];
    }

    public synchronized void reserve(int seatNumber) {
        if (seatNumber < 0 || seatNumber >= reservedSeats.length) {
            return;
        }
        if (!reservedSeats[seatNumber]) {
            reservedSeats[seatNumber] = true;
            totalBooked++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Task5 bookingSystem = new Task5(10);
        Thread[] customers = new Thread[50];

        for (int i = 0; i < customers.length; i++) {
            int requestedSeat = i % 10;
            customers[i] = new Thread(() -> bookingSystem.reserve(requestedSeat));
            customers[i].start();
        }

        for (Thread customer : customers) {
            customer.join();
        }
        System.out.println("Unique seats booked: " + bookingSystem.totalBooked);
    }
}

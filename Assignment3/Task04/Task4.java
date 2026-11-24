class LabWorker implements Runnable {
    private final String workerName;

    LabWorker(String workerName) {
        this.workerName = workerName;
    }

    @Override
    public void run() {
        try {
            System.out.println(workerName + " started.");
            Thread.sleep(400);
            System.out.println(workerName + " finished processing.");
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            System.out.println(workerName + " was interrupted.");
        }
    }
}

public class Task4 {
    public static void main(String[] args) throws InterruptedException {
        Thread[] workers = new Thread[4];

        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Thread(new LabWorker("Worker " + (i + 1)));
            workers[i].start();
        }

        for (Thread worker : workers) {
            worker.join();
        }
        System.out.println("All worker threads have completed.");
    }
}

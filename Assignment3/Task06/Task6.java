public class Task6 {
    private final int[] buffer = new int[5];
    private int count;
    private int writeIndex;
    private int readIndex;

    public synchronized void put(int value) throws InterruptedException {
        while (count == buffer.length) {
            wait();
        }
        buffer[writeIndex] = value;
        writeIndex = (writeIndex + 1) % buffer.length;
        count++;
        notifyAll();
    }

    public synchronized int take() throws InterruptedException {
        while (count == 0) {
            wait();
        }
        int value = buffer[readIndex];
        readIndex = (readIndex + 1) % buffer.length;
        count--;
        notifyAll();
        return value;
    }

    public static void main(String[] args) throws InterruptedException {
        Task6 queue = new Task6();
        int[] consumerTotals = new int[2];

        Thread producer = new Thread(() -> {
            try {
                for (int value = 1; value <= 20; value++) {
                    queue.put(value);
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        });

        Thread[] consumers = new Thread[2];
        for (int i = 0; i < consumers.length; i++) {
            final int index = i;
            consumers[i] = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        consumerTotals[index] += queue.take();
                    }
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        producer.start();
        for (Thread consumer : consumers) {
            consumer.start();
        }

        producer.join();
        for (Thread consumer : consumers) {
            consumer.join();
        }

        System.out.println("Total of consumed values: "
                + (consumerTotals[0] + consumerTotals[1]));
    }
}

import java.util.LinkedList;

public class BookingService {

    public static final LinkedList<BookRequest> requestList = new LinkedList<>();

    public static volatile int quantityOfGeneratedRequests = 0;

    public static final int MAX_QUANTITY_OF_GENERATED_REQUESTS = 15;
    public static final int MAX_QUANTITY_OF_REQUESTS_IN_QUEUE = 5;

    public void startGeneration() {
        ProducingThread producingThread1 = new ProducingThread();
        producingThread1.start();

        ProducingThread producingThread2 = new ProducingThread();
        producingThread2.start();

        ProducingThread producingThread3 = new ProducingThread();
        producingThread3.start();

        ConsumingThread consumingThread1 = new ConsumingThread();
        consumingThread1.start();

        ConsumingThread consumingThread2 = new ConsumingThread();
        consumingThread2.start();

        ConsumingThread consumingThread3 = new ConsumingThread();
        consumingThread3.start();

        ConsumingThread consumingThread4 = new ConsumingThread();
        consumingThread4.start();

        ConsumingThread consumingThread5 = new ConsumingThread();
        consumingThread5.start();

        ConsumingThread consumingThread6 = new ConsumingThread();
        consumingThread6.start();
    }
}

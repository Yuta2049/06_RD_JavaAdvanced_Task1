import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumingThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(ConsumingThread.class);

    @Override
    public void run() {

        synchronized (BookingService.requestList) {

            while (BookingService.quantityOfGeneratedRequests < BookingService.MAX_QUANTITY_OF_GENERATED_REQUESTS ||
                    BookingService.requestList.size() > 0) {

                try {

                    if (BookingService.requestList.size() > 0) {
                        BookRequest bookRequest = BookingService.requestList.getFirst();
                        logger.info("booker: #" + currentThread().getName() + ": received: " + bookRequest.getId() + ". (Length of queue = " + BookingService.requestList.size() + ")");

                        Thread.sleep(5000);

                        bookRequest = BookingService.requestList.removeFirst();
                        BookingService.requestList.notifyAll();
                        logger.info("booker: #" + currentThread().getName() + ": processed: " + bookRequest.getId() + ". (Length of queue = " + BookingService.requestList.size() + ")");
                    } else if (BookingService.quantityOfGeneratedRequests < BookingService.MAX_QUANTITY_OF_GENERATED_REQUESTS) {
                        BookingService.requestList.wait();
                    }

                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    logger.error("consumer: #" + currentThread().getName() + ": Error in sleep");
                }
            }
        }
    }
}
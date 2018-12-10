import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumingThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(ConsumingThread.class);

    @Override
    public void run() {

        synchronized (BookingService.requestList) {

            while (BookingService.quantityOfGeneratedRequests < BookingService.MAX_QUANTITY_OF_GENERATED_REQUESTS ||
                    !BookingService.requestList.isEmpty()) {

                try {

                    if (!BookingService.requestList.isEmpty()) {
                        BookRequest bookRequest = BookingService.requestList.removeFirst();

                        logger.info("booker: #" + currentThread().getName() + ": received: " + bookRequest.getId() + ". (Length of queue = " + BookingService.requestList.size() + ")");

                        BookingService.requestList.wait();
                        Thread.sleep(5000);

                        BookingService.requestList.notifyAll();

                        logger.info("booker: #" + currentThread().getName() + ": processed: " + bookRequest.getId() + ". (Length of queue = " + BookingService.requestList.size() + ")");
                    } else if (BookingService.quantityOfGeneratedRequests < BookingService.MAX_QUANTITY_OF_GENERATED_REQUESTS) {
                        BookingService.requestList.wait();
                    }

                } catch (InterruptedException e) {
                    logger.error("consumer: #" + currentThread().getName() + ": Error in sleep");
                }
            }
        }
    }
}
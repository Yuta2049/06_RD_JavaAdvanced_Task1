import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducingThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(ProducingThread.class);

    @Override
    public void run() {

        synchronized (BookingService.requestList) {

            while (BookingService.quantityOfGeneratedRequests < BookingService.MAX_QUANTITY_OF_GENERATED_REQUESTS) {

                if (BookingService.requestList.size() < BookingService.MAX_QUANTITY_OF_REQUESTS_IN_QUEUE) {
                    BookRequest bookRequest = new BookRequest();
                    BookingService.requestList.addLast(bookRequest);
                    BookingService.quantityOfGeneratedRequests++;
                    logger.info("producer: #" + currentThread().getName() + ": sent: " + bookRequest.getId() + ". (Length of queue = " + BookingService.requestList.size() + ")");

                    BookingService.requestList.notifyAll();
                } else {
                    try {
                        BookingService.requestList.wait();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        logger.error("producer: #" + currentThread().getName() + ": Error in wait");
                    }
                }
            }
        }
    }
}
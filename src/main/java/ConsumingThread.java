import com.sun.corba.se.impl.resolver.BootstrapResolverImpl;

import java.awt.print.Book;

public class ConsumingThread extends Thread {

    @Override
    public void run() {

        while (BookingService.quantityOfGeneratedRequests < BookingService.maxQuantityOfGeneratedRequests) {

            //if (BookingService.quantityOfRequestsInQueue > 0) {

            try {
                Thread.sleep(5000);

                if (BookingService.requestList.size() > 0) {

                    BookRequest bookRequest = BookingService.requestList.removeFirst();
                    System.out.println(currentThread().getName()+". Удалили из очереди элемент: "+bookRequest.getId()+". Размер очереди: "+BookingService.requestList.size());

                }

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                System.out.println("Ошибка при отправке потока в sleep, Поток: " + currentThread().getName());
            }
        }
    }
}
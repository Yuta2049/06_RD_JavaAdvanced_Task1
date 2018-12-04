import com.sun.corba.se.impl.resolver.BootstrapResolverImpl;

import java.awt.print.Book;

public class ConsumingThread extends Thread {

    @Override
    public void run() {


        synchronized (BookingService.requestList) {

            while (BookingService.quantityOfGeneratedRequests < BookingService.maxQuantityOfGeneratedRequests ||
                BookingService.requestList.size() > 0) {
                //currentThread().interrupt();

//                System.out.println("BookingService.quantityOfGeneratedRequests = "+BookingService.quantityOfGeneratedRequests);
//                System.out.println("BookingService.maxQuantityOfGeneratedRequests = "+BookingService.maxQuantityOfGeneratedRequests);
//                System.out.println("BookingService.requestList.size() = "+BookingService.requestList.size());


            //while (BookingService.quantityOfGeneratedRequests < BookingService.maxQuantityOfGeneratedRequests) {



                //if (BookingService.quantityOfRequestsInQueue > 0) {

                try {

                    if (BookingService.requestList.size() > 0) {

                        BookRequest bookRequest = BookingService.requestList.removeFirst();
                        BookingService.requestList.notifyAll();

                        System.out.println(currentThread().getName() + ". Удалили из очереди элемент: " + bookRequest.getId() + ". Размер очереди: " + BookingService.requestList.size());

                    }

                    Thread.sleep(5000);


                    // Немножко бред?
                    if (BookingService.quantityOfGeneratedRequests < BookingService.maxQuantityOfGeneratedRequests ||
                            BookingService.requestList.size() > 0) {
                        BookingService.requestList.wait();
                    }


                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Ошибка при отправке потока в sleep, Поток: " + currentThread().getName());
                }
            }
            System.out.println(currentThread().getName()+" Консьюмер - вышли из цикла");
        }
    }
}
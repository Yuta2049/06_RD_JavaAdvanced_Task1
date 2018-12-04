public class ProducingThread extends Thread {

    @Override
    public void run() {


        synchronized (BookingService.requestList) {

            while (BookingService.quantityOfGeneratedRequests < BookingService.maxQuantityOfGeneratedRequests) {

                if (BookingService.requestList.size() < BookingService.maxQuantityOfRequestsInQueue) {
                    BookRequest bookRequest = new BookRequest();
                    BookingService.requestList.add(bookRequest);
                    BookingService.quantityOfGeneratedRequests++;
                    System.out.println(currentThread().getName() + ". Сгенерировали добавили в очередь элемент: " + bookRequest.getId() + ". Размер очереди: " + BookingService.requestList.size());
                    BookingService.requestList.notifyAll();
                } else {
                    try {
                        BookingService.requestList.wait();
                    } catch (Exception e) {
                        System.out.println("Ошибка при wait в продьюсере");
                    }
                }
            }

            System.out.println(currentThread().getName()+" Продьюсер - вышли из цикла");
        }
    }
}
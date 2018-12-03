public class ProducingThread extends Thread {

    @Override
    public void run() {

        while (BookingService.quantityOfGeneratedRequests < BookingService.maxQuantityOfGeneratedRequests) {

//            System.out.println(currentThread().getName()+" cur = "+BookingService.quantityOfGeneratedRequests);
//            System.out.println(currentThread().getName()+" max = "+BookingService.maxQuantityOfGeneratedRequests);
//
//            System.out.println(currentThread().getName()+" size of queue = "+BookingService.requestList.size());
//            System.out.println(currentThread().getName()+" max in queue = "+BookingService.maxQuantityOfRequestsInQueue);

            if (BookingService.requestList.size() < BookingService.maxQuantityOfRequestsInQueue) {
                BookRequest bookRequest = new BookRequest();
                BookingService.requestList.add(bookRequest);
                BookingService.quantityOfGeneratedRequests++;
                System.out.println(currentThread().getName()+". Сгенерировали добавили в очередь элемент: "+bookRequest.getId()+". Размер очереди: "+BookingService.requestList.size());


            }
        }
    }
}
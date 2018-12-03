import java.time.LocalDate;
import java.util.Random;

public class BookRequest {

    private Long id;
    private Hotel hotel;
    private Client client;
    private LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookRequest() {
        //Random random = new Random(1_000_000);
        //this.id = random.nextLong();

        Random random = new Random();
        this.id = random.nextLong();

    }
}

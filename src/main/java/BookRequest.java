import java.util.Random;

public class BookRequest {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookRequest() {
        Random random = new Random();
        this.id = random.nextLong();
    }
}

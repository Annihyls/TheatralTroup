import java.util.UUID;

public class Customer {
    private String name;
    private UUID numCustomer;
    private int sold;

    public Customer(String name, int sold){
        this.name = name;
        this.numCustomer = UUID.randomUUID();
        this.sold = sold;
    }

    public boolean isAvailableForAReduction() {
        boolean result = false;
        if(this.sold > 150) {
            this.sold -= 150;
            result = true;
        }
        return result;
    }
}
